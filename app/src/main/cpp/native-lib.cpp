#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_gblatestversion_gbversion_gb_utils_Constant_getMainApi(
        JNIEnv *env, jclass clazz) {

    std::string hello = "https://pixelmatticapps.space/nv/Gb488/V1/";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL

Java_com_gblatestversion_gbversion_gb_utils_Constant_getKey1(
        JNIEnv *env, jclass clazz) {

    std::string hello = "qVMekSZlG8R1zHFO";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_gblatestversion_gbversion_gb_utils_Constant_getKey2(
        JNIEnv *env, jclass clazz) {

    std::string hello = "traiXqZdFTBHxXRB";
    return env->NewStringUTF(hello.c_str());
}

