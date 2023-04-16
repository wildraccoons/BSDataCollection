package frc.teamdata;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

class SyncPipe implements Runnable
{
public SyncPipe(InputStream istrm) {
      istrm_ = istrm;
  }
  public void run() {
      try
      {
          final byte[] buffer = new byte[1024];
          ArrayList<String> str = new ArrayList<>();
          for (int length = 0; (length = istrm_.read(buffer)) != -1; )
          {
              String details = new String(buffer, 0, length);
              str.add(details);
          }
          Files.write( Paths.get("C:/Users/DesignTeam/Downloads/BSDataCollection/data-collection/src/main/java/frc/teamdata/jarContents.txt"), str.toString().getBytes());
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
    }
  private final InputStream istrm_;
}
