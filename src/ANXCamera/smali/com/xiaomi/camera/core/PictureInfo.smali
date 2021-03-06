.class public Lcom/xiaomi/camera/core/PictureInfo;
.super Ljava/lang/Object;
.source "PictureInfo.java"


# static fields
.field private static final MIRROR:Ljava/lang/String; = "mirror"

.field private static final SENSOR_TYPE:Ljava/lang/String; = "sensor_type"

.field private static final SENSOR_TYPE_FRONT:Ljava/lang/String; = "front"

.field private static final SENSOR_TYPE_REAR:Ljava/lang/String; = "rear"

.field private static final TAG:Ljava/lang/String;


# instance fields
.field private aiType:I

.field private isBokehFrontCamera:Z

.field private isMirror:Z

.field private transient mInfo:Lorg/json/JSONObject;

.field private mInfoString:Ljava/lang/String;

.field private mSensorType:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 10
    const-class v0, Lcom/xiaomi/camera/core/PictureInfo;

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/xiaomi/camera/core/PictureInfo;->TAG:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 22
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 15
    const-string v0, "rear"

    iput-object v0, p0, Lcom/xiaomi/camera/core/PictureInfo;->mSensorType:Ljava/lang/String;

    .line 23
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    iput-object v0, p0, Lcom/xiaomi/camera/core/PictureInfo;->mInfo:Lorg/json/JSONObject;

    .line 24
    return-void
.end method


# virtual methods
.method public end()V
    .locals 1

    .line 73
    iget-object v0, p0, Lcom/xiaomi/camera/core/PictureInfo;->mInfo:Lorg/json/JSONObject;

    invoke-virtual {v0}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/xiaomi/camera/core/PictureInfo;->mInfoString:Ljava/lang/String;

    .line 74
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/xiaomi/camera/core/PictureInfo;->mInfo:Lorg/json/JSONObject;

    .line 75
    return-void
.end method

.method public getAiType()I
    .locals 1

    .line 60
    iget v0, p0, Lcom/xiaomi/camera/core/PictureInfo;->aiType:I

    return v0
.end method

.method public getInfoString()Ljava/lang/String;
    .locals 1

    .line 69
    iget-object v0, p0, Lcom/xiaomi/camera/core/PictureInfo;->mInfoString:Ljava/lang/String;

    return-object v0
.end method

.method public isBokehFrontCamera()Z
    .locals 1

    .line 51
    iget-boolean v0, p0, Lcom/xiaomi/camera/core/PictureInfo;->isBokehFrontCamera:Z

    return v0
.end method

.method public isFrontMirror()Z
    .locals 1

    .line 47
    iget-boolean v0, p0, Lcom/xiaomi/camera/core/PictureInfo;->isMirror:Z

    return v0
.end method

.method public setAiType(I)Lcom/xiaomi/camera/core/PictureInfo;
    .locals 0

    .line 64
    iput p1, p0, Lcom/xiaomi/camera/core/PictureInfo;->aiType:I

    .line 65
    return-object p0
.end method

.method public setBokehFrontCamera(Z)Lcom/xiaomi/camera/core/PictureInfo;
    .locals 0

    .line 55
    iput-boolean p1, p0, Lcom/xiaomi/camera/core/PictureInfo;->isBokehFrontCamera:Z

    .line 56
    return-object p0
.end method

.method public setFrontMirror(Z)Lcom/xiaomi/camera/core/PictureInfo;
    .locals 2

    .line 37
    iput-boolean p1, p0, Lcom/xiaomi/camera/core/PictureInfo;->isMirror:Z

    .line 39
    :try_start_0
    iget-object v0, p0, Lcom/xiaomi/camera/core/PictureInfo;->mInfo:Lorg/json/JSONObject;

    const-string v1, "mirror"

    invoke-virtual {v0, v1, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Z)Lorg/json/JSONObject;
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    .line 42
    goto :goto_0

    .line 40
    :catch_0
    move-exception p1

    .line 41
    sget-object v0, Lcom/xiaomi/camera/core/PictureInfo;->TAG:Ljava/lang/String;

    const-string v1, "setFrontMirror JSONException occurs "

    invoke-static {v0, v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 43
    :goto_0
    return-object p0
.end method

.method public setSensorType(Z)Lcom/xiaomi/camera/core/PictureInfo;
    .locals 2

    .line 27
    if-eqz p1, :cond_0

    const-string p1, "front"

    goto :goto_0

    :cond_0
    const-string p1, "rear"

    :goto_0
    iput-object p1, p0, Lcom/xiaomi/camera/core/PictureInfo;->mSensorType:Ljava/lang/String;

    .line 29
    :try_start_0
    iget-object p1, p0, Lcom/xiaomi/camera/core/PictureInfo;->mInfo:Lorg/json/JSONObject;

    const-string v0, "sensor_type"

    iget-object v1, p0, Lcom/xiaomi/camera/core/PictureInfo;->mSensorType:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    .line 32
    goto :goto_1

    .line 30
    :catch_0
    move-exception p1

    .line 31
    sget-object v0, Lcom/xiaomi/camera/core/PictureInfo;->TAG:Ljava/lang/String;

    const-string v1, "setSensorType JSONException occurs "

    invoke-static {v0, v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 33
    :goto_1
    return-object p0
.end method
