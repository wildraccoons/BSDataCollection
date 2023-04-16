package frc.teamdata;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.lang.CharSequence;

class SyncPipe implements Runnable
{
public SyncPipe(InputStream istrm, OutputStream ostrm) {
      istrm_ = istrm;
      ostrm_ = ostrm;
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
  private final OutputStream ostrm_;
  private final InputStream istrm_;
}
