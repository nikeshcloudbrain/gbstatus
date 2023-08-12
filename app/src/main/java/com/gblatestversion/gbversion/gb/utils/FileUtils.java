package com.gblatestversion.gbversion.gb.utils;

import android.os.Build;
import android.os.Environment;

public class FileUtils {

    public static final String AUDIO = "audio";
    public static final String DOCUMENT = "document";
    public static final String GIF = "gif";
    public static final String IMAGE = "image";
    public static final String VIDEO = "video";
    public static final String VOICE = "status";
    public static final String WALLPAPER = "wallpaper";


    public static String mString = Build.VERSION.SDK_INT <= 30 ? "/WhatsApp/Media" : "/Android/media/com.whatsapp/WhatsApp/Media";
    public static String mStringBusiness = Build.VERSION.SDK_INT <= 30 ? "/WhatsApp Business/Media" : "/Android/media/com.whatsapp.w4b/WhatsApp Business/Media";

    public static final String audiosReceivedPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WhatsApp Audio");
    public static final String audiosSentPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WhatsApp Audio/Sent");

    public static final String documentsReceivedPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WhatsApp Documents");
    public static final String documentsSentPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WhatsApp Documents/Sent");

    public static final String gifReceivedPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WhatsApp Animated Gifs");
    public static final String gifSentPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WhatsApp Animated Gifs/Sent");

    public static final String imagesReceivedPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WhatsApp Images");
    public static final String imagesSentPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WhatsApp Images/Sent");

    public static final String videosReceivedPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WhatsApp Video");
    public static final String videosSentPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WhatsApp Video/Sent");

    public static final String voiceReceivedPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WhatsApp Voice Notes");
    public static final String wallReceivedPath = (Environment.getExternalStorageDirectory().toString() + mString + "/WallPaper");

    public static final String wbAudiosReceivedPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WhatsApp Business Audio");
    public static final String wbAudiosSentPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WhatsApp Business Audio/Sent");

    public static final String wbDocumentsReceivedPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WhatsApp Business Documents");
    public static final String wbDocumentsSentPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WhatsApp Business Documents/Sent");

    public static final String wbGifReceivedPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WhatsApp Business Animated Gifs");
    public static final String wbGifSentPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WhatsApp Business Animated Gifs/Sent");

    public static final String wbImagesReceivedPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WhatsApp Business Images");
    public static final String wbImagesSentPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WhatsApp Business Images/Sent");

    public static final String wbVideosReceivedPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WhatsApp Business Video");
    public static final String wbVideosSentPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WhatsApp Business Video/Sent");

    public static final String wbVoiceReceivedPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WhatsApp Business Voice Notes");
    public static final String wbWallReceivedPath = (Environment.getExternalStorageDirectory().toString() + mStringBusiness + "/WallPaper");

}
