package nfs;

import com.emc.ecs.nfsclient.nfs.io.Nfs3File;
import com.emc.ecs.nfsclient.nfs.io.NfsFileInputStream;
import com.emc.ecs.nfsclient.nfs.nfs3.Nfs3;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public class NfsService {
    private static Logger LOG = LoggerFactory.getLogger(NfsService.class);

    public boolean validateNfs(String ip, String sharedPath) {
        try {
            new Nfs3(MessageFormat.format("{0}:{1}", ip, sharedPath), 0, 0, 3);
            return true;
        } catch (IOException e) {
            LOG.error("Can't connect to nfs {}" ,e);
            return false;
        }
    }

    public boolean doesFileExist(String ip, String sharedPath, String filePath) {
        if (!filePath.startsWith("/")) {
            throw new IllegalArgumentException("filePath should start with /");
        }
        Nfs3 nfs3Client;
        try {
            nfs3Client = new Nfs3(MessageFormat.format("{0}:{1}", ip, sharedPath), 0, 0, 3);
        } catch (IOException e) {
            LOG.error("Can't connect to nfs {}" ,e);
            throw new NfsServerException(e);
        }
        try {
            Nfs3File nfs3File;
            nfs3File = new Nfs3File(nfs3Client, filePath);
            return nfs3File.exists();
        } catch (IOException e) {
            LOG.error("Unexpected exception with NFS {}" ,e);
            throw new NfsServerException(e);
        }
    }

    public void downloadFile(String ip, String sharedPath, String filePath, String targetPath) {
        if (!filePath.startsWith("/")) {
            throw new IllegalArgumentException("filePath should start with /");
        }
        Nfs3 nfs3Client;
        try {
            nfs3Client = new Nfs3(MessageFormat.format("{0}:{1}", ip, sharedPath), 0, 0, 3);
        } catch (IOException e) {
            LOG.error("Can't connect to nfs {}" ,e);
            throw new NfsServerException(e);
        }
        try {
            Nfs3File nfs3File;
            nfs3File = new Nfs3File(nfs3Client, filePath);
            NfsFileInputStream nis = new NfsFileInputStream(nfs3File);
            File targetFile = new File(targetPath);
            FileUtils.copyInputStreamToFile(nis, targetFile);
        } catch (IOException e) {
            LOG.error("Unexpected exception with NFS {}" ,e);
            throw new NfsServerException(e);
        }
    }

    public long getFileSize(String ip, String sharedPath, String filePath) {
        if (!filePath.startsWith("/")) {
            throw new IllegalArgumentException("filePath should start with /");
        }
        Nfs3 nfs3Client;
        try {
            nfs3Client = new Nfs3(MessageFormat.format("{0}:{1}", ip, sharedPath), 0, 0, 3);
        } catch (IOException e) {
            LOG.error("Can't connect to nfs {}" ,e);
            throw new NfsServerException(e);
        }
        try {
            Nfs3File nfs3File;
            nfs3File = new Nfs3File(nfs3Client, filePath);
            return nfs3File.length();
        } catch (IOException e) {
            LOG.error("Unexpected exception with NFS {}" ,e);
            throw new NfsServerException(e);
        }
    }

}
