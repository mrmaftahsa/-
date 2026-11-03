package ma.baby.meals;
import android.app.*; import android.content.*; import android.os.Build;
import java.time.*;
public class MealReceiver extends BroadcastReceiver {
 static android.content.SharedPreferences prefs(Context c){return c.getSharedPreferences("baby",0);}
 static java.time.LocalDate birth(Context c){String s=prefs(c).getString("birth",""); return s.isEmpty()?null:LocalDate.parse(s);}
 static PendingIntent alarm(Context c,int s){return PendingIntent.getBroadcast(c,s,new Intent(c,MealReceiver.class).setAction("meal").putExtra("slot",s),PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);}
 static void schedule(Context c){
 AlarmManager am=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
 for(int s=0;s<3;s++){
 am.cancel(alarm(c,s)); LocalDate b=birth(c);
 if(b==null || !prefs(c).getBoolean("enabled",false) || !Plan.active(prefs(c).getInt("count",1),s))continue;
 int minutes=prefs(c).getInt("time"+s,new int[]{540,780,1080}[s]);
 ZonedDateTime now=ZonedDateTime.now(); ZonedDateTime next=now.toLocalDate().atTime(minutes/60,minutes%60).atZone(ZoneId.systemDefault());
 if(!next.isAfter(now))next=next.plusDays(1);
 if(Plan.supported(b,next.toLocalDate()))am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,next.toInstant().toEpochMilli(),alarm(c,s));
 }
 }
 static void notifyMeal(Context c,int s,boolean test){
 NotificationManager nm=(NotificationManager)c.getSystemService(Context.NOTIFICATION_SERVICE);
 nm.createNotificationChannel(new NotificationChannel("meals","تذكيرات الوجبات",NotificationManager.IMPORTANCE_DEFAULT));
 LocalDate b=birth(c); if(b==null)return;
 String body=test?"التذكيرات تعمل. يمكنك تعديل أوقاتها في الإعدادات.":Meals.title(c,b,LocalDate.now(),s);
 PendingIntent open=PendingIntent.getActivity(c,0,new Intent(c,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
 if(Build.VERSION.SDK_INT>=33 && c.checkSelfPermission("android.permission.POST_NOTIFICATIONS")!=android.content.pm.PackageManager.PERMISSION_GRANTED)return;
 nm.notify(s,new Notification.Builder(c,"meals").setSmallIcon(android.R.drawable.ic_popup_reminder).setContentTitle(test?"تجربة تذكير لقمتي":Plan.LABELS[s]).setContentText(body).setStyle(new Notification.BigTextStyle().bigText(body)).setContentIntent(open).setAutoCancel(true).build());
 }
 @Override public void onReceive(Context c,Intent i){
 if("meal".equals(i.getAction())){int s=i.getIntExtra("slot",1);LocalDate b=birth(c);
 if(b!=null && prefs(c).getBoolean("enabled",false) && Plan.supported(b,LocalDate.now()) && Plan.active(prefs(c).getInt("count",1),s))notifyMeal(c,s,false);}
 schedule(c);
 }
}

