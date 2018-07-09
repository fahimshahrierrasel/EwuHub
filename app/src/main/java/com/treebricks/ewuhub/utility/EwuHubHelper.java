package com.treebricks.ewuhub.utility;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class EwuHubHelper {

    public static void copyDatabase(Context ctx, String filename) {
        AssetManager assetManager = ctx.getAssets();

        InputStream input = null;
        OutputStream output = null;
        try {
            Log.i("tag", "copyHTMLFile() " + filename);
            input = assetManager.open(filename);
            File DatabaseDirectory = new File(ctx.getApplicationInfo().dataDir + "/databases");
            if (!DatabaseDirectory.exists()) {
                DatabaseDirectory.mkdirs();
                Log.i("Directory Log :", "Directory Created" + DatabaseDirectory);
            }

            output = new FileOutputStream(DatabaseDirectory + "/" + filename);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = input.read(buffer)) != -1) {
                output.write(buffer, 0, read);
            }
        } catch (Exception e) {
            Log.e("tag", "Exception in copyHTMLFile() of " + filename);
            Log.e("tag", "Exception in copyHTMLFile() " + e.toString());
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }
        }
    }

    public static void copyJsonFile(Context ctx, String filename) {
        AssetManager assetManager = ctx.getAssets();

        InputStream input = null;
        OutputStream output = null;
        try {
            Log.i("JSONFileCopy", "copyHTMLFile() " + filename);
            input = assetManager.open(filename);
            File JsonDirectory = new File(ctx.getApplicationInfo().dataDir + "/json");
            if (!JsonDirectory.exists()) {
                JsonDirectory.mkdirs();
                Log.i("Directory Log :", "Directory Created" + JsonDirectory);
            }

            output = new FileOutputStream(JsonDirectory + "/" + filename);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = input.read(buffer)) != -1) {
                output.write(buffer, 0, read);
            }
        } catch (Exception e) {
            Log.e("JSONFileCopy", "Exception in copyHTMLFile() of " + filename);
            Log.e("JSONFileCopy", "Exception in copyHTMLFile() " + e.toString());
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }
        }
    }

    // Function for read JSON String from file.
    public static String readJSONStringFromFile(Context ctx, String fileName) {
        String json = null;
        try {
            File file = new File(ctx.getApplicationInfo().dataDir + "/json/" + fileName);
            if (file.exists()) {
                InputStream is = new FileInputStream(file);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
