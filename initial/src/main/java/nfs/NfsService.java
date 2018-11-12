package nfs;

import com.emc.ecs.nfsclient.nfs.io.Nfs3File;
import com.emc.ecs.nfsclient.nfs.nfs3.Nfs3;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class NfsService {

    private Nfs3 nfs3Client;

    public void init(String ip) {
        try {
            nfs3Client = new Nfs3(ip + ":/var/nfs", 0, 0 , 3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void foo() {
        try {
            Nfs3File testFile = new Nfs3File(nfs3Client, "/dummy");
            testFile.createNewFile();
            Nfs3File root = new Nfs3File(nfs3Client, "/");
            System.out.println(root.list());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
