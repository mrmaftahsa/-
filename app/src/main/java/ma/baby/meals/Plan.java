package ma.baby.meals;
import java.time.*;
import java.time.temporal.ChronoUnit;
public final class Plan {
 public static final String[] LABELS={"الفطور","الغداء","العشاء"};
 public static int months(LocalDate birth,LocalDate day){return (int)ChronoUnit.MONTHS.between(birth,day);}
 public static boolean supported(LocalDate birth,LocalDate day){return !day.isBefore(birth.plusMonths(6)) && day.isBefore(birth.plusYears(1));}
 public static boolean active(int count,int slot){return count==3 || slot==1 || (count==2 && slot==0);}
 static final String[][] FOOD={
 {"شوفان مطبوخ مع كمثرى","بطاطا حلوة مهروسة","شوفان مع موز","تفاح مطبوخ مهروس","شوفان مع كمثرى","موز مهروس","بطاطا حلوة مع شوفان"},
 {"عدس أحمر مع جزر","دجاج مع قرع أخضر وبطاطا","لحم بقري مع قرع أحمر","عدس أحمر مع بطاطا حلوة","دجاج مع جزر","لحم بقري مع بطاطا وقرع أخضر","عدس أحمر مع قرع أحمر"},
 {"قرع أحمر مع بطاطا","شوفان مع تفاح مطبوخ","بطاطا حلوة مع قرع أخضر","جزر مع بطاطا","شوفان مع كمثرى","قرع أحمر مع بطاطا","قرع أخضر مع بطاطا"}};
 public static String meal(LocalDate birth,LocalDate day,int slot){
 if(!supported(birth,day)) return "الخطة مخصصة لعمر 6 أشهر إلى ما قبل السنة";
 int i=Math.floorMod(ChronoUnit.DAYS.between(birth.plusMonths(6),day),7);
 return FOOD[slot][i];
 }
 public static String texture(int age){return age<9?"اطبخي جيدا واهرسي إلى قوام يناسب قدرتها، ثم زيدي الخشونة تدريجيا.":"اطبخي حتى يصبح الطعام طريا جدا؛ اهرسي أو قدمي قطعا لينة مناسبة لقدرتها.";}
 public static String preparation(int slot,int age){return texture(age)+" اطبخي المكونات في قليل من الماء دون ملح أو مكعبات مرق. اطبخي اللحم والدجاج تماما وأزيلي الجلد والعظام وافرميهما ناعما. ابدئي بكمية صغيرة وزيدي حسب الجوع؛ لا تجبريها على إكمال الطبق.";}
}
