#include "com_marakana_android_lognative_LogLib.h"
#include <android/log.h>

JNIEXPORT void JNICALL Java_com_marakana_android_lognative_LogLib_log
  (JNIEnv *env, jclass clazz, jint priority, jstring tag, jstring message) {
	const jbyte *str_tag;
	const jbyte *str_message;

	// Convert Java strings to C
	str_tag = (*env)->GetStringUTFChars(env, tag, 0);
	str_message = (*env)->GetStringUTFChars(env, message, 0);

	// Call library function to log
	__android_log_print(priority, str_tag, str_message);

	// Release the strings
	(*env)->ReleaseStringUTFChars(env, tag, str_tag);
	(*env)->ReleaseStringUTFChars(env, message, str_message);
}



