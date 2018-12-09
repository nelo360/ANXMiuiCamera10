.class final Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;
.super Ljava/lang/Object;
.source "SingleFromPublisher.java"

# interfaces
.implements Lio/reactivex/FlowableSubscriber;
.implements Lio/reactivex/disposables/Disposable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lio/reactivex/internal/operators/single/SingleFromPublisher;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "ToSingleObserver"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Lio/reactivex/FlowableSubscriber<",
        "TT;>;",
        "Lio/reactivex/disposables/Disposable;"
    }
.end annotation


# instance fields
.field final actual:Lio/reactivex/SingleObserver;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lio/reactivex/SingleObserver<",
            "-TT;>;"
        }
    .end annotation
.end field

.field volatile disposed:Z

.field done:Z

.field s:Lorg/reactivestreams/Subscription;

.field value:Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TT;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>(Lio/reactivex/SingleObserver;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lio/reactivex/SingleObserver<",
            "-TT;>;)V"
        }
    .end annotation

    .line 49
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 50
    iput-object p1, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->actual:Lio/reactivex/SingleObserver;

    .line 51
    return-void
.end method


# virtual methods
.method public dispose()V
    .locals 1

    .line 112
    const/4 v0, 0x1

    iput-boolean v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->disposed:Z

    .line 113
    iget-object v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->s:Lorg/reactivestreams/Subscription;

    invoke-interface {v0}, Lorg/reactivestreams/Subscription;->cancel()V

    .line 114
    return-void
.end method

.method public isDisposed()Z
    .locals 1

    .line 107
    iget-boolean v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->disposed:Z

    return v0
.end method

.method public onComplete()V
    .locals 3

    .line 92
    iget-boolean v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->done:Z

    if-eqz v0, :cond_0

    .line 93
    return-void

    .line 95
    :cond_0
    const/4 v0, 0x1

    iput-boolean v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->done:Z

    .line 96
    iget-object v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->value:Ljava/lang/Object;

    .line 97
    const/4 v1, 0x0

    iput-object v1, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->value:Ljava/lang/Object;

    .line 98
    if-nez v0, :cond_1

    .line 99
    iget-object v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->actual:Lio/reactivex/SingleObserver;

    new-instance v1, Ljava/util/NoSuchElementException;

    const-string v2, "The source Publisher is empty"

    invoke-direct {v1, v2}, Ljava/util/NoSuchElementException;-><init>(Ljava/lang/String;)V

    invoke-interface {v0, v1}, Lio/reactivex/SingleObserver;->onError(Ljava/lang/Throwable;)V

    goto :goto_0

    .line 101
    :cond_1
    iget-object v1, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->actual:Lio/reactivex/SingleObserver;

    invoke-interface {v1, v0}, Lio/reactivex/SingleObserver;->onSuccess(Ljava/lang/Object;)V

    .line 103
    :goto_0
    return-void
.end method

.method public onError(Ljava/lang/Throwable;)V
    .locals 1

    .line 81
    iget-boolean v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->done:Z

    if-eqz v0, :cond_0

    .line 82
    invoke-static {p1}, Lio/reactivex/plugins/RxJavaPlugins;->onError(Ljava/lang/Throwable;)V

    .line 83
    return-void

    .line 85
    :cond_0
    const/4 v0, 0x1

    iput-boolean v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->done:Z

    .line 86
    const/4 v0, 0x0

    iput-object v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->value:Ljava/lang/Object;

    .line 87
    iget-object v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->actual:Lio/reactivex/SingleObserver;

    invoke-interface {v0, p1}, Lio/reactivex/SingleObserver;->onError(Ljava/lang/Throwable;)V

    .line 88
    return-void
.end method

.method public onNext(Ljava/lang/Object;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    .line 66
    iget-boolean v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->done:Z

    if-eqz v0, :cond_0

    .line 67
    return-void

    .line 69
    :cond_0
    iget-object v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->value:Ljava/lang/Object;

    if-eqz v0, :cond_1

    .line 70
    iget-object p1, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->s:Lorg/reactivestreams/Subscription;

    invoke-interface {p1}, Lorg/reactivestreams/Subscription;->cancel()V

    .line 71
    const/4 p1, 0x1

    iput-boolean p1, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->done:Z

    .line 72
    const/4 p1, 0x0

    iput-object p1, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->value:Ljava/lang/Object;

    .line 73
    iget-object p1, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->actual:Lio/reactivex/SingleObserver;

    new-instance v0, Ljava/lang/IndexOutOfBoundsException;

    const-string v1, "Too many elements in the Publisher"

    invoke-direct {v0, v1}, Ljava/lang/IndexOutOfBoundsException;-><init>(Ljava/lang/String;)V

    invoke-interface {p1, v0}, Lio/reactivex/SingleObserver;->onError(Ljava/lang/Throwable;)V

    goto :goto_0

    .line 75
    :cond_1
    iput-object p1, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->value:Ljava/lang/Object;

    .line 77
    :goto_0
    return-void
.end method

.method public onSubscribe(Lorg/reactivestreams/Subscription;)V
    .locals 2

    .line 55
    iget-object v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->s:Lorg/reactivestreams/Subscription;

    invoke-static {v0, p1}, Lio/reactivex/internal/subscriptions/SubscriptionHelper;->validate(Lorg/reactivestreams/Subscription;Lorg/reactivestreams/Subscription;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 56
    iput-object p1, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->s:Lorg/reactivestreams/Subscription;

    .line 58
    iget-object v0, p0, Lio/reactivex/internal/operators/single/SingleFromPublisher$ToSingleObserver;->actual:Lio/reactivex/SingleObserver;

    invoke-interface {v0, p0}, Lio/reactivex/SingleObserver;->onSubscribe(Lio/reactivex/disposables/Disposable;)V

    .line 60
    const-wide v0, 0x7fffffffffffffffL

    invoke-interface {p1, v0, v1}, Lorg/reactivestreams/Subscription;->request(J)V

    .line 62
    :cond_0
    return-void
.end method