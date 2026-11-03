package ma.baby.meals;
import android.app.*;import android.content.*;import android.view.*;import android.widget.*;import androidx.test.platform.app.InstrumentationRegistry;import androidx.test.core.app.ActivityScenario;import org.junit.Test;import static org.junit.Assert.*;import java.time.*;import java.lang.reflect.*;import org.json.*;
public class AppSmokeTest {
 private void call(MainActivity a,String name)throws Exception{Method m=MainActivity.class.getDeclaredMethod(name);m.setAccessible(true);m.invoke(a);}
 @Test public void arabicScreensAndDiary()throws Exception{
 Instrumentation ins=InstrumentationRegistry.getInstrumentation();Context c=ins.getTargetContext();c.getSharedPreferences("baby",0).edit().clear().putString("birth",LocalDate.now().minusMonths(6).toString()).putInt("count",3).commit();
 try(ActivityScenario<MainActivity> scene=ActivityScenario.launch(MainActivity.class)){
 scene.onActivity(a->{assertEquals("ar",a.getResources().getConfiguration().getLocales().get(0).getLanguage());ViewGroup content=a.findViewById(android.R.id.content);assertEquals(View.LAYOUT_DIRECTION_RTL,content.getChildAt(0).getLayoutDirection());});
 ins.waitForIdleSync();capture(ins,c,"home");
 for(String name:new String[]{"shopping","diary","foodSettings","settings","guide"}){scene.onActivity(a->{try{call(a,name);}catch(Exception e){throw new AssertionError(e);}});ins.waitForIdleSync();capture(ins,c,name);}
 scene.onActivity(a->{try{Method validator=MainActivity.class.getDeclaredMethod("validateDiary",String.class);validator.setAccessible(true);validator.invoke(a,"[]");try{validator.invoke(a,"[{\"title\":\"bad\"}]");fail("Invalid diary accepted");}catch(InvocationTargetException expected){}}catch(Exception e){throw new AssertionError(e);}});
 }
 }
 private void capture(Instrumentation ins,Context c,String name)throws Exception{
 String command="mkdir -p /sdcard/Download/luqmati-screenshots && screencap -p /sdcard/Download/luqmati-screenshots/"+name+".png";
 try(android.os.ParcelFileDescriptor fd=ins.getUiAutomation().executeShellCommand("sh -c \""+command+"\"");java.io.FileInputStream in=new java.io.FileInputStream(fd.getFileDescriptor())){byte[] buffer=new byte[1024];while(in.read(buffer)!=-1){}}
 }
}
