.class Lcom/android/camera/fragment/top/FragmentTopAlert$3;
.super Ljava/lang/Object;
.source "FragmentTopAlert.java"

# interfaces
.implements Lcom/android/camera/ui/ToggleSwitch$OnCheckedChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/android/camera/fragment/top/FragmentTopAlert;->alertMoonSelector(Ljava/lang/String;Ljava/lang/String;II)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/camera/fragment/top/FragmentTopAlert;


# direct methods
.method constructor <init>(Lcom/android/camera/fragment/top/FragmentTopAlert;)V
    .locals 0

    .line 397
    iput-object p1, p0, Lcom/android/camera/fragment/top/FragmentTopAlert$3;->this$0:Lcom/android/camera/fragment/top/FragmentTopAlert;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCheckedChanged(Lcom/android/camera/ui/ToggleSwitch;Z)V
    .locals 1

    .line 400
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p1

    const/16 v0, 0xa4

    invoke-virtual {p1, v0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p1

    check-cast p1, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;

    .line 401
    if-eqz p2, :cond_0

    .line 402
    if-eqz p1, :cond_1

    .line 403
    const/16 p2, 0xf6

    invoke-interface {p1, p2}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->onConfigChanged(I)V

    goto :goto_0

    .line 406
    :cond_0
    if-eqz p1, :cond_1

    .line 407
    const/16 p2, 0xf7

    invoke-interface {p1, p2}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->onConfigChanged(I)V

    .line 410
    :cond_1
    :goto_0
    return-void
.end method