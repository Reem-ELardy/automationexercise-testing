package utils.Framework;

import java.io.File;

public class FileUtils {

    public static File waitForExactFile(String directoryPath,
                                        String fileName,
                                        int timeoutSeconds) {

        File file = new File(directoryPath + File.separator + fileName);
        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);

        while (System.currentTimeMillis() < endTime) {

            if (file.exists() && file.length() > 0) {
                return file;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("File wait interrupted", e);
            }
        }

        throw new AssertionError(
                "File '" + fileName + "' was not downloaded in " + directoryPath
        );
    }
}
