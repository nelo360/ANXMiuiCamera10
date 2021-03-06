package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscribers.QueueDrainSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableBufferTimed<T, U extends Collection<? super T>> extends AbstractFlowableWithUpstream<T, U> {
    final Callable<U> bufferSupplier;
    final int maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final long timeskip;
    final long timespan;
    final TimeUnit unit;

    static final class BufferExactBoundedSubscriber<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Disposable, Runnable, Subscription {
        U buffer;
        final Callable<U> bufferSupplier;
        long consumerIndex;
        final int maxSize;
        long producerIndex;
        final boolean restartTimerOnMaxSize;
        Subscription s;
        Disposable timer;
        final long timespan;
        final TimeUnit unit;
        final Worker w;

        BufferExactBoundedSubscriber(Subscriber<? super U> subscriber, Callable<U> callable, long j, TimeUnit timeUnit, int i, boolean z, Worker worker) {
            super(subscriber, new MpscLinkedQueue());
            this.bufferSupplier = callable;
            this.timespan = j;
            this.unit = timeUnit;
            this.maxSize = i;
            this.restartTimerOnMaxSize = z;
            this.w = worker;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    this.actual.onSubscribe(this);
                    this.timer = this.w.schedulePeriodically(this, this.timespan, this.timespan, this.unit);
                    subscription.request(Long.MAX_VALUE);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.w.dispose();
                    subscription.cancel();
                    EmptySubscription.error(th, this.actual);
                }
            }
        }

        /* JADX WARNING: Missing block: B:13:0x0021, code:
            if (r7.restartTimerOnMaxSize == false) goto L_0x0028;
     */
        /* JADX WARNING: Missing block: B:14:0x0023, code:
            r7.timer.dispose();
     */
        /* JADX WARNING: Missing block: B:15:0x0028, code:
            fastPathOrderedEmitMax(r0, false, r7);
     */
        /* JADX WARNING: Missing block: B:17:?, code:
            r8 = (java.util.Collection) io.reactivex.internal.functions.ObjectHelper.requireNonNull(r7.bufferSupplier.call(), "The supplied buffer is null");
     */
        /* JADX WARNING: Missing block: B:18:0x003b, code:
            monitor-enter(r7);
     */
        /* JADX WARNING: Missing block: B:20:?, code:
            r7.buffer = r8;
            r7.consumerIndex++;
     */
        /* JADX WARNING: Missing block: B:21:0x0043, code:
            monitor-exit(r7);
     */
        /* JADX WARNING: Missing block: B:23:0x0046, code:
            if (r7.restartTimerOnMaxSize == false) goto L_0x0057;
     */
        /* JADX WARNING: Missing block: B:24:0x0048, code:
            r7.timer = r7.w.schedulePeriodically(r7, r7.timespan, r7.timespan, r7.unit);
     */
        /* JADX WARNING: Missing block: B:25:0x0057, code:
            return;
     */
        /* JADX WARNING: Missing block: B:30:0x005b, code:
            r8 = move-exception;
     */
        /* JADX WARNING: Missing block: B:31:0x005c, code:
            io.reactivex.exceptions.Exceptions.throwIfFatal(r8);
            cancel();
            r7.actual.onError(r8);
     */
        /* JADX WARNING: Missing block: B:32:0x0067, code:
            return;
     */
        public void onNext(T r8) {
            /*
            r7 = this;
            monitor-enter(r7);
            r0 = r7.buffer;	 Catch:{ all -> 0x0068 }
            if (r0 != 0) goto L_0x0007;
        L_0x0005:
            monitor-exit(r7);	 Catch:{ all -> 0x0068 }
            return;
        L_0x0007:
            r0.add(r8);	 Catch:{ all -> 0x0068 }
            r8 = r0.size();	 Catch:{ all -> 0x0068 }
            r1 = r7.maxSize;	 Catch:{ all -> 0x0068 }
            if (r8 >= r1) goto L_0x0014;
        L_0x0012:
            monitor-exit(r7);	 Catch:{ all -> 0x0068 }
            return;
        L_0x0014:
            r8 = 0;
            r7.buffer = r8;	 Catch:{ all -> 0x0068 }
            r1 = r7.producerIndex;	 Catch:{ all -> 0x0068 }
            r3 = 1;
            r1 = r1 + r3;
            r7.producerIndex = r1;	 Catch:{ all -> 0x0068 }
            monitor-exit(r7);	 Catch:{ all -> 0x0068 }
            r8 = r7.restartTimerOnMaxSize;
            if (r8 == 0) goto L_0x0028;
        L_0x0023:
            r8 = r7.timer;
            r8.dispose();
        L_0x0028:
            r8 = 0;
            r7.fastPathOrderedEmitMax(r0, r8, r7);
            r8 = r7.bufferSupplier;	 Catch:{ Throwable -> 0x005b }
            r8 = r8.call();	 Catch:{ Throwable -> 0x005b }
            r0 = "The supplied buffer is null";
            r8 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r8, r0);	 Catch:{ Throwable -> 0x005b }
            r8 = (java.util.Collection) r8;	 Catch:{ Throwable -> 0x005b }
            monitor-enter(r7);
            r7.buffer = r8;	 Catch:{ all -> 0x0058 }
            r0 = r7.consumerIndex;	 Catch:{ all -> 0x0058 }
            r0 = r0 + r3;
            r7.consumerIndex = r0;	 Catch:{ all -> 0x0058 }
            monitor-exit(r7);	 Catch:{ all -> 0x0058 }
            r8 = r7.restartTimerOnMaxSize;
            if (r8 == 0) goto L_0x0057;
        L_0x0048:
            r0 = r7.w;
            r2 = r7.timespan;
            r4 = r7.timespan;
            r6 = r7.unit;
            r1 = r7;
            r8 = r0.schedulePeriodically(r1, r2, r4, r6);
            r7.timer = r8;
        L_0x0057:
            return;
        L_0x0058:
            r8 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x0058 }
            throw r8;
        L_0x005b:
            r8 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r8);
            r7.cancel();
            r0 = r7.actual;
            r0.onError(r8);
            return;
        L_0x0068:
            r8 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x0068 }
            throw r8;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableBufferTimed.BufferExactBoundedSubscriber.onNext(java.lang.Object):void");
        }

        public void onError(Throwable th) {
            synchronized (this) {
                this.buffer = null;
            }
            this.actual.onError(th);
            this.w.dispose();
        }

        public void onComplete() {
            Collection collection;
            synchronized (this) {
                collection = this.buffer;
                this.buffer = null;
            }
            this.queue.offer(collection);
            this.done = true;
            if (enter()) {
                QueueDrainHelper.drainMaxLoop(this.queue, this.actual, false, this, this);
            }
            this.w.dispose();
        }

        public boolean accept(Subscriber<? super U> subscriber, U u) {
            subscriber.onNext(u);
            return true;
        }

        public void request(long j) {
            requested(j);
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                dispose();
            }
        }

        public void dispose() {
            synchronized (this) {
                this.buffer = null;
            }
            this.s.cancel();
            this.w.dispose();
        }

        public boolean isDisposed() {
            return this.w.isDisposed();
        }

        public void run() {
            try {
                Collection collection = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                synchronized (this) {
                    Collection collection2 = this.buffer;
                    if (collection2 == null || this.producerIndex != this.consumerIndex) {
                        return;
                    }
                    this.buffer = collection;
                    fastPathOrderedEmitMax(collection2, false, this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancel();
                this.actual.onError(th);
            }
        }
    }

    static final class BufferExactUnboundedSubscriber<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Disposable, Runnable, Subscription {
        U buffer;
        final Callable<U> bufferSupplier;
        Subscription s;
        final Scheduler scheduler;
        final AtomicReference<Disposable> timer = new AtomicReference();
        final long timespan;
        final TimeUnit unit;

        BufferExactUnboundedSubscriber(Subscriber<? super U> subscriber, Callable<U> callable, long j, TimeUnit timeUnit, Scheduler scheduler) {
            super(subscriber, new MpscLinkedQueue());
            this.bufferSupplier = callable;
            this.timespan = j;
            this.unit = timeUnit;
            this.scheduler = scheduler;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    this.actual.onSubscribe(this);
                    if (!this.cancelled) {
                        subscription.request(Long.MAX_VALUE);
                        Disposable schedulePeriodicallyDirect = this.scheduler.schedulePeriodicallyDirect(this, this.timespan, this.timespan, this.unit);
                        if (!this.timer.compareAndSet(null, schedulePeriodicallyDirect)) {
                            schedulePeriodicallyDirect.dispose();
                        }
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    cancel();
                    EmptySubscription.error(th, this.actual);
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                Collection collection = this.buffer;
                if (collection != null) {
                    collection.add(t);
                }
            }
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.timer);
            synchronized (this) {
                this.buffer = null;
            }
            this.actual.onError(th);
        }

        /* JADX WARNING: Missing block: B:9:0x0010, code:
            r4.queue.offer(r0);
            r4.done = true;
     */
        /* JADX WARNING: Missing block: B:10:0x001c, code:
            if (enter() == false) goto L_0x0026;
     */
        /* JADX WARNING: Missing block: B:11:0x001e, code:
            io.reactivex.internal.util.QueueDrainHelper.drainMaxLoop(r4.queue, r4.actual, false, null, r4);
     */
        /* JADX WARNING: Missing block: B:12:0x0026, code:
            return;
     */
        public void onComplete() {
            /*
            r4 = this;
            r0 = r4.timer;
            io.reactivex.internal.disposables.DisposableHelper.dispose(r0);
            monitor-enter(r4);
            r0 = r4.buffer;	 Catch:{ all -> 0x0027 }
            if (r0 != 0) goto L_0x000c;
        L_0x000a:
            monitor-exit(r4);	 Catch:{ all -> 0x0027 }
            return;
        L_0x000c:
            r1 = 0;
            r4.buffer = r1;	 Catch:{ all -> 0x0027 }
            monitor-exit(r4);	 Catch:{ all -> 0x0027 }
            r2 = r4.queue;
            r2.offer(r0);
            r0 = 1;
            r4.done = r0;
            r0 = r4.enter();
            if (r0 == 0) goto L_0x0026;
        L_0x001e:
            r0 = r4.queue;
            r2 = r4.actual;
            r3 = 0;
            io.reactivex.internal.util.QueueDrainHelper.drainMaxLoop(r0, r2, r3, r1, r4);
        L_0x0026:
            return;
        L_0x0027:
            r0 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x0027 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableBufferTimed.BufferExactUnboundedSubscriber.onComplete():void");
        }

        public void request(long j) {
            requested(j);
        }

        public void cancel() {
            this.s.cancel();
            DisposableHelper.dispose(this.timer);
        }

        public void run() {
            try {
                Collection collection;
                Collection collection2 = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                synchronized (this) {
                    collection = this.buffer;
                    if (collection != null) {
                        this.buffer = collection2;
                    }
                }
                if (collection == null) {
                    DisposableHelper.dispose(this.timer);
                } else {
                    fastPathEmitMax(collection, false, this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancel();
                this.actual.onError(th);
            }
        }

        public boolean accept(Subscriber<? super U> subscriber, U u) {
            this.actual.onNext(u);
            return true;
        }

        public void dispose() {
            cancel();
        }

        public boolean isDisposed() {
            return this.timer.get() == DisposableHelper.DISPOSED;
        }
    }

    static final class BufferSkipBoundedSubscriber<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Runnable, Subscription {
        final Callable<U> bufferSupplier;
        final List<U> buffers = new LinkedList();
        Subscription s;
        final long timeskip;
        final long timespan;
        final TimeUnit unit;
        final Worker w;

        final class RemoveFromBuffer implements Runnable {
            private final U buffer;

            RemoveFromBuffer(U u) {
                this.buffer = u;
            }

            public void run() {
                synchronized (BufferSkipBoundedSubscriber.this) {
                    BufferSkipBoundedSubscriber.this.buffers.remove(this.buffer);
                }
                BufferSkipBoundedSubscriber.this.fastPathOrderedEmitMax(this.buffer, false, BufferSkipBoundedSubscriber.this.w);
            }
        }

        BufferSkipBoundedSubscriber(Subscriber<? super U> subscriber, Callable<U> callable, long j, long j2, TimeUnit timeUnit, Worker worker) {
            super(subscriber, new MpscLinkedQueue());
            this.bufferSupplier = callable;
            this.timespan = j;
            this.timeskip = j2;
            this.unit = timeUnit;
            this.w = worker;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                try {
                    Collection collection = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    this.buffers.add(collection);
                    this.actual.onSubscribe(this);
                    subscription.request(Long.MAX_VALUE);
                    this.w.schedulePeriodically(this, this.timeskip, this.timeskip, this.unit);
                    this.w.schedule(new RemoveFromBuffer(collection), this.timespan, this.unit);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.w.dispose();
                    subscription.cancel();
                    EmptySubscription.error(th, this.actual);
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                for (Collection add : this.buffers) {
                    add.add(t);
                }
            }
        }

        public void onError(Throwable th) {
            this.done = true;
            this.w.dispose();
            clear();
            this.actual.onError(th);
        }

        public void onComplete() {
            synchronized (this) {
                List<Collection> arrayList = new ArrayList(this.buffers);
                this.buffers.clear();
            }
            for (Collection offer : arrayList) {
                this.queue.offer(offer);
            }
            this.done = true;
            if (enter()) {
                QueueDrainHelper.drainMaxLoop(this.queue, this.actual, false, this.w, this);
            }
        }

        public void request(long j) {
            requested(j);
        }

        public void cancel() {
            clear();
            this.s.cancel();
            this.w.dispose();
        }

        void clear() {
            synchronized (this) {
                this.buffers.clear();
            }
        }

        public void run() {
            if (!this.cancelled) {
                try {
                    Collection collection = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    synchronized (this) {
                        if (this.cancelled) {
                            return;
                        }
                        this.buffers.add(collection);
                        this.w.schedule(new RemoveFromBuffer(collection), this.timespan, this.unit);
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    cancel();
                    this.actual.onError(th);
                }
            }
        }

        public boolean accept(Subscriber<? super U> subscriber, U u) {
            subscriber.onNext(u);
            return true;
        }
    }

    public FlowableBufferTimed(Flowable<T> flowable, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, Callable<U> callable, int i, boolean z) {
        super(flowable);
        this.timespan = j;
        this.timeskip = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.bufferSupplier = callable;
        this.maxSize = i;
        this.restartTimerOnMaxSize = z;
    }

    protected void subscribeActual(Subscriber<? super U> subscriber) {
        if (this.timespan == this.timeskip && this.maxSize == Integer.MAX_VALUE) {
            this.source.subscribe(new BufferExactUnboundedSubscriber(new SerializedSubscriber(subscriber), this.bufferSupplier, this.timespan, this.unit, this.scheduler));
            return;
        }
        Worker createWorker = this.scheduler.createWorker();
        if (this.timespan == this.timeskip) {
            this.source.subscribe(new BufferExactBoundedSubscriber(new SerializedSubscriber(subscriber), this.bufferSupplier, this.timespan, this.unit, this.maxSize, this.restartTimerOnMaxSize, createWorker));
        } else {
            this.source.subscribe(new BufferSkipBoundedSubscriber(new SerializedSubscriber(subscriber), this.bufferSupplier, this.timespan, this.timeskip, this.unit, createWorker));
        }
    }
}
