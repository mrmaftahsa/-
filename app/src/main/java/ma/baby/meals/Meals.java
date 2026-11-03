package ma.baby.meals;
import android.content.*;import java.time.*;import java.util.*;
public final class Meals {
 static Set<String> marked(SharedPreferences p,String prefix){Set<String>s=new HashSet<>();for(Catalog.Food f:Catalog.FOODS)if(p.getBoolean(prefix+f.id,false))s.add(f.id);return s;}
 static Catalog.Recipe selected(Context c,LocalDate birth,LocalDate day,int slot){SharedPreferences p=MealReceiver.prefs(c);return Catalog.choose(birth,day,slot,p.getInt("swap:"+day+":"+slot,-1),marked(p,"avoid:"),marked(p,"introduced:"));}
 static String title(Context c,LocalDate birth,LocalDate day,int slot){Catalog.Recipe r=selected(c,birth,day,slot);return r==null?"لا توجد وصفة مناسبة لاختياراتك الحالية":r.name;}
}
