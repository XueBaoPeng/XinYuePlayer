package music.dreamer.useful;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
/**歌曲扫描类**/
/*This work comes from Dreamer丶Team. The main programmer is LinShaoHan.
 * QQ:752280466   Welcome to join with us.
 */
public class ScanSdReceiver extends BroadcastReceiver {

	private AlertDialog.Builder  builder = null;
	private AlertDialog ad = null;
	private int count1;
	private int count2;
	private int count;
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(action)){
			Cursor c1 = context.getContentResolver()
					.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
							new String[]{MediaStore.Audio.Media.TITLE,
									MediaStore.Audio.Media.DURATION,
									MediaStore.Audio.Media.ARTIST,
									MediaStore.Audio.Media._ID,
									MediaStore.Audio.Media.DISPLAY_NAME },
							null, null, null);
			count1 = c1.getCount();
			System.out.println("count:"+count);
			builder = new AlertDialog.Builder(context);
			builder.setMessage("正在刷新音乐库...");
			ad = builder.create();
			ad.show();

		}else if(Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(action)){
			ad.cancel();
			/*Toast.makeText(context,"刷新完毕！", Toast.LENGTH_LONG).show();*/
		}
	}
}
