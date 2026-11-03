package ma.baby.meals;
import android.app.*; import android.os.*; import android.content.*; import android.graphics.Color; import android.graphics.Typeface; import android.graphics.drawable.GradientDrawable; import android.view.*; import android.widget.*;
import java.time.*;
public class MainActivity extends Activity {
 LinearLayout root; LocalDate shown=LocalDate.now(); int green=Color.rgb(34,92,73); int ink=Color.rgb(38,55,46);
 android.content.SharedPreferences p(){return MealReceiver.prefs(this);}
 @Override public void onCreate(Bundle b){super.onCreate(b);home();}
 @Override public void onResume(){super.onResume();MealReceiver.schedule(this);}
 int dp(int x){return (int)(x*getResources().getDisplayMetrics().density);}
 void base(){ScrollView sc=new ScrollView(this);root=new LinearLayout(this);root.setOrientation(1);root.setPadding(dp(22),dp(38),dp(22),dp(28));root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);root.setBackgroundColor(Color.rgb(249,246,239));sc.addView(root);setContentView(sc);}
 TextView text(String t,int size){TextView v=new TextView(this);v.setText(t);v.setTextSize(size);v.setTextColor(ink);v.setPadding(0,dp(7),0,dp(7));root.addView(v);return v;}
 void button(String t,Runnable r){Button b=new Button(this);b.setText(t);b.setTextColor(green);root.addView(b);b.setOnClickListener(v->r.run());}
 void title(String t){text(t,30).setTypeface(null,Typeface.BOLD);}
 void home(){base();title("لقمتي 🌿");text("نكهات مغربية • خطوات صغيرة كل يوم",16);
 LocalDate birth=MealReceiver.birth(this);if(birth==null){text("مرحبا بكما! اختاري تاريخ الميلاد الحقيقي لحساب العمر. الخطة من 6 أشهر إلى عيد الميلاد الأول.",20);button("اختيار تاريخ الميلاد",()->pickBirth());return;}
 text("برنامج يوم "+shown+" • العمر "+Plan.months(birth,shown)+" أشهر",18);
 button("الإعدادات والتذكيرات",()->settings());
 text("حليب الأم أو الحليب الصناعي يبقى أساس التغذية خلال السنة الأولى. هذه أفكار وجبات وليست وصفة طبية.",16);
 if(Plan.supported(birth,shown)){
 int count=p().getInt("count",1);
 for(int s=0;s<3;s++){final int slot=s; int time=p().getInt("time"+s,new int[]{540,780,1080}[s]);
 TextView head=text(Plan.LABELS[s]+"  •  "+String.format(java.util.Locale.ROOT,"%02d:%02d",time/60,time%60),22);head.setTypeface(null,Typeface.BOLD);
 if(!Plan.active(count,s)){text("غير مفعلة حاليا — استمري في الرضعات حسب حاجتها",16);continue;}
 TextView food=text(Plan.meal(birth,shown,s),21);GradientDrawable bg=new GradientDrawable();bg.setColor(Color.WHITE);bg.setCornerRadius(dp(14));food.setBackground(bg);food.setPadding(dp(15),dp(15),dp(15),dp(15));
 button("طريقة التحضير",()->new AlertDialog.Builder(this).setTitle(Plan.meal(birth,shown,slot)).setMessage(Plan.preparation(slot,Plan.months(birth,shown))).setPositiveButton("حسنا",null).show());
 CheckBox done=new CheckBox(this);done.setText("تم تقديم الوجبة");String key="done:"+shown+":"+s;done.setChecked(p().getBoolean(key,false));done.setOnCheckedChangeListener((v,on)->p().edit().putBoolean(key,on).apply());root.addView(done);
 }}else text("انتهت الخطة أو لم يبدأ عمرها المناسب بعد. لا توجد وجبات أو تذكيرات خارج عمر 6–11 شهرا.",20);
 button("اليوم التالي ←",()->{shown=shown.plusDays(1);home();});button("→ اليوم السابق",()->{shown=shown.minusDays(1);home();});button("العودة إلى اليوم",()->{shown=LocalDate.now();home();});button("دليل التغذية والأمان",()->guide());
 }
 void pickBirth(){LocalDate d=MealReceiver.birth(this);if(d==null)d=LocalDate.now().minusMonths(6);DatePickerDialog dialog=new DatePickerDialog(this,(v,y,m,day)->{p().edit().putString("birth",LocalDate.of(y,m+1,day).toString()).apply();shown=LocalDate.now();MealReceiver.schedule(this);home();},d.getYear(),d.getMonthValue()-1,d.getDayOfMonth());dialog.getDatePicker().setMaxDate(System.currentTimeMillis());dialog.show();}
 void settings(){base();title("على إيقاع طفلتك");button("تعديل تاريخ الميلاد",()->pickBirth());text("ابدئي بكميات قليلة مرة يوميا عند بدء الطعام، ثم زيدي تدريجيا حسب استعدادها. عادة 2–3 وجبات بعمر 6–8 أشهر، ونحو 3 وجبات بعمر 9–11 شهرا. لا تتغير الوجبات المفعلة دون اختيارك.",17);
 String[] options={"وجبة واحدة: الغداء","وجبتان: الفطور والغداء","ثلاث وجبات"};
 for(int i=0;i<3;i++){final int n=i+1;button((p().getInt("count",1)==n?"✓ ":"")+options[i],()->{p().edit().putInt("count",n).apply();MealReceiver.schedule(this);settings();});}
 for(int s=0;s<3;s++){final int slot=s;int tm=p().getInt("time"+s,new int[]{540,780,1080}[s]);button("وقت "+Plan.LABELS[s]+" • "+String.format(java.util.Locale.ROOT,"%02d:%02d",tm/60,tm%60),()->new TimePickerDialog(this,(v,h,m)->{p().edit().putInt("time"+slot,h*60+m).apply();MealReceiver.schedule(this);settings();},tm/60,tm%60,true).show());}
 Switch sw=new Switch(this);sw.setText("تفعيل التذكيرات اليومية");sw.setChecked(p().getBoolean("enabled",false));root.addView(sw);sw.setOnCheckedChangeListener((v,on)->{p().edit().putBoolean("enabled",on).apply();if(on && Build.VERSION.SDK_INT>=33 && checkSelfPermission("android.permission.POST_NOTIFICATIONS")!=android.content.pm.PackageManager.PERMISSION_GRANTED)requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"},4);MealReceiver.schedule(this);});
 text("التذكيرات تقريبية وقد تتأخر بسبب توفير البطارية. عند إيقاف التطبيق إجباريا، افتحيه مجددا لاستئنافها.",16);
 NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);text(nm.areNotificationsEnabled()?"إذن الإشعارات متاح":"الإشعارات محظورة في إعدادات الهاتف",16);
 button("إعدادات إشعارات الهاتف",()->startActivity(new Intent("android.settings.APP_NOTIFICATION_SETTINGS").putExtra("android.provider.extra.APP_PACKAGE",getPackageName())));
 button("تجربة إشعار الآن",()->MealReceiver.notifyMeal(this,1,true));button("العودة إلى البرنامج",()->home());
 }
 void guide(){base();title("بداية مطمئنة");text("• ابدئي قرب 6 أشهر عندما تستطيع الجلوس مع ثبات الرأس وتنسيق اليد والعين والفم وبلع الطعام.

• الطعام مكمل للرضعات. قدمي رشفات ماء مع الوجبة؛ لا تجعلي حليب البقر مشروبا رئيسيا قبل السنة.

• اجعليها جالسة بشكل مستقيم وراقبيها طوال الأكل. لا مكسرات كاملة، لا عنب كامل، لا قطع جزر نيئة ولا قطع صلبة.

• لا عسل قبل 12 شهرا، ولا ملح أو سكر مضاف أو مكعبات مرق. الشاي والعصير غير ضروريين.

• هذه دورة أفكار من 7 أيام تتكرر؛ ليست 180 وصفة مختلفة ولا نظاما غذائيا متكاملا. استعملي مكونات سبق تحملها في الوصفات المركبة.

• أدخلي مسببات الحساسية واحدا في كل مرة وبكمية صغيرة نهارا: مثل البيض المطبوخ تماما، القمح، اللبن الطبيعي المبستر كامل الدسم، السمك منزوع الشوك أو معجون المكسرات المخفف داخل الطعام. إذا تم تحملها استمري في تقديمها بانتظام. لا تؤخريها لمجرد غيابها عن الدورة الأساسية. الشوفان قد يحتوي على آثار قمح.

• عند أكزيما شديدة أو حساسية معروفة استشيري الطبيب قبل إدخال مسببات الحساسية. إذا ظهر تفاعل أوقفي الطعام واطلبي المشورة. صعوبة التنفس أو تورم اللسان أو انهيار الطفل حالة طارئة.

• اغسلي اليدين والأدوات، اطبخي اللحم والدجاج تماما، وقدمي الطعام بعد أن يبرد لحرارة مناسبة. تخلصي مما بقي في طبق الطفلة.

• الكمية والقوام حسب قدرتها وشهيتها. اعرضي هذه الأفكار على طبيبها إذا كان لديها صعوبة بلع أو مشكلة نمو.",18);
 button("مصدر: منظمة الصحة العالمية",()->startActivity(new Intent(Intent.ACTION_VIEW,android.net.Uri.parse("https://www.who.int/health-topics/complementary-feeding"))));button("مصدر: إرشادات NHS",()->startActivity(new Intent(Intent.ACTION_VIEW,android.net.Uri.parse("https://www.nhs.uk/baby/weaning-and-feeding/babys-first-solid-foods/"))));button("العودة",()->home());}
}
