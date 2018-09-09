package study.rationalegoism.mvp_rx_study.model;

import android.os.AsyncTask;

import com.bumptech.glide.request.FutureTarget;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import study.rationalegoism.mvp_rx_study.AppContextSingleton;
import timber.log.Timber;

public class DownloadImageAsyncTask extends AsyncTask<FutureTarget<File>, Void, Void>{


    private String sameImage(File imageFile) {
        String savedImagePath = null;

        String imageFileName = imageFile.getName() + ".jpg";
        File storageDir = new File(AppContextSingleton.getInstance().getContext()
                .getApplicationInfo().dataDir, "images");
        File destFile = new File(storageDir, imageFileName);
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            savedImagePath = destFile.getAbsolutePath();
            try {
                FileUtils.copyFile(imageFile, destFile);
                //TODO check Exception
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return savedImagePath;
    }

    @Override
    protected Void doInBackground(FutureTarget<File>... futureTargets) {
        try {
            File imageFile = futureTargets[0].get();
            sameImage(imageFile);
        } catch (InterruptedException | ExecutionException e) {
            Timber.i(e.getMessage());
        }
        return null;
    }
}
