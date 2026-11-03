package ma.baby.meals;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
public final class Catalog {
 public static final class Food {public final String id,name,prep,allergen; Food(String i,String n,String p,String a){id=i;name=n;prep=p;allergen=a;}}
 public static final class Recipe {public final int id,slot; public final String name; public final String[] foods; Recipe(int i,int s,String n,String f){id=i;slot=s;name=n;foods=f.split(" ");}}
 public static final Food[] FOODS={
new Food("oats","الشوفان","اغلي الشوفان في الماء على نار هادئة حتى يلين تماما، ثم اهرسيه عند الحاجة.",""),
new Food("pear","الإجاص (الكمثرى)","اغسلي الإجاص وأزيلي القشرة والبذور. اطبخيه بالبخار إذا كان قاسيا، ثم اهرسيه.",""),
new Food("sweet","البطاطا الحلوة","قشري البطاطا الحلوة وقطعيها، ثم اطبخيها حتى يسهل هرسها بالشوكة.",""),
new Food("banana","الموز","قشري موزة ناضجة واهرسي الجزء المطلوب بالشوكة؛ لا يحتاج إلى طبخ.",""),
new Food("apple","التفاح","قشري التفاح وأزيلي البذور، ثم اطبخيه في قليل من الماء حتى يلين تماما واهرسيه.",""),
new Food("lentil","العدس الأحمر","اغسلي العدس الأحمر واطبخيه في الماء حتى يتفكك ويلين تماما، ثم اهرسيه.",""),
new Food("carrot","الجزر","قشري الجزر واطبخيه حتى يلين تماما ثم اهرسيه. لا تقدميه نيئا أو على شكل دوائر صلبة.",""),
new Food("chicken","الدجاج","أزيلي الجلد والعظام واطبخي الدجاج تماما، ثم افرميه ناعما ورطبيه بماء الطبخ.",""),
new Food("zucchini","القرع الأخضر","اغسلي القرع الأخضر واطبخيه بالبخار حتى يلين، ثم اهرسيه. أزيلي القشرة إن كانت قاسية.",""),
new Food("pumpkin","القرع الأحمر","أزيلي قشرة القرع الأحمر والبذور، ثم اطبخيه حتى يلين واهرسيه.",""),
new Food("beef","اللحم البقري","اختاري لحما طريا قليل الدهن واطبخيه تماما. افرميه ناعما جدا ورطبيه بماء الطبخ.",""),
new Food("potato","البطاطا","قشري البطاطا واطبخيها في الماء حتى تلين، ثم اهرسيها مع قليل من ماء الطبخ.",""),
new Food("egg","البيض","اطبخي البيضة حتى يتماسك الصفار والبياض تماما، ثم اهرسيها ورطبيها مع المكون الآخر.","البيض"),
new Food("yogurt","الياغورت الطبيعي","استعملي ياغورت طبيعيا مبسترا كامل الدسم، بدون سكر أو عسل. امزجيه بالمكون الآخر بعد أن يبرد.","الحليب"),
new Food("couscous","الكسكس الرقيق","اطبخي الكسكس الرقيق جيدا دون ملح حتى يصبح طريا جدا، ثم رطبيه واهرسيه بالقوام المناسب.","القمح"),
new Food("fish","السمك الأبيض","اطبخي سمكا أبيض قليل الزئبق تماما. أزيلي الجلد وكل الشوك بعناية ثم اهرسيه وافحصيه مرة أخرى.","السمك")};
 public static final Recipe[] RECIPES={
new Recipe(0,0,"شوفان بالإجاص","oats pear"),
new Recipe(1,0,"شوفان بالموز","oats banana"),
new Recipe(2,0,"شوفان بالتفاح","oats apple"),
new Recipe(3,0,"مهروس البطاطا الحلوة بالشوفان","sweet oats"),
new Recipe(4,0,"ياغورت بالموز","yogurt banana"),
new Recipe(5,0,"ياغورت بالإجاص","yogurt pear"),
new Recipe(6,0,"شوفان بالقرع الأحمر","oats pumpkin"),
new Recipe(7,0,"مهروس التفاح والإجاص","apple pear"),
new Recipe(8,0,"كسكس طري بالتفاح","couscous apple"),
new Recipe(9,0,"بيض مهروس بالبطاطا","egg potato"),
new Recipe(10,0,"شوفان بالتفاح والموز","oats apple banana"),
new Recipe(11,0,"بطاطا حلوة بالإجاص","sweet pear"),
new Recipe(12,1,"عدس بالجزر","lentil carrot"),
new Recipe(13,1,"دجاج بالقرع الأخضر والبطاطا","chicken zucchini potato"),
new Recipe(14,1,"لحم بالقرع الأحمر","beef pumpkin"),
new Recipe(15,1,"عدس بالبطاطا الحلوة","lentil sweet"),
new Recipe(16,1,"دجاج بالجزر والبطاطا","chicken carrot potato"),
new Recipe(17,1,"لحم بالقرع الأخضر","beef zucchini"),
new Recipe(18,1,"عدس بالقرع الأحمر","lentil pumpkin"),
new Recipe(19,1,"سمك بالبطاطا والقرع الأخضر","fish potato zucchini"),
new Recipe(20,1,"كسكس بالدجاج والجزر","couscous chicken carrot"),
new Recipe(21,1,"عدس بالجزر والقرع الأخضر","lentil carrot zucchini"),
new Recipe(22,1,"لحم بالبطاطا الحلوة","beef sweet"),
new Recipe(23,1,"دجاج بالقرع الأحمر","chicken pumpkin"),
new Recipe(24,2,"مهروس القرع الأحمر والبطاطا","pumpkin potato"),
new Recipe(25,2,"شوفان بالتفاح والإجاص","oats apple pear"),
new Recipe(26,2,"مهروس البطاطا الحلوة والقرع الأخضر","sweet zucchini"),
new Recipe(27,2,"مهروس الجزر والبطاطا","carrot potato"),
new Recipe(28,2,"عدس بالقرع الأخضر","lentil zucchini"),
new Recipe(29,2,"بيض بالقرع الأخضر","egg zucchini"),
new Recipe(30,2,"مهروس القرع الأخضر والبطاطا","zucchini potato"),
new Recipe(31,2,"شوفان بالبطاطا الحلوة والموز","oats sweet banana"),
new Recipe(32,2,"مهروس القرع الأحمر والجزر","pumpkin carrot"),
new Recipe(33,2,"كسكس بالقرع الأحمر","couscous pumpkin"),
new Recipe(34,2,"عدس بالجزر والبطاطا","lentil carrot potato"),
new Recipe(35,2,"مهروس البطاطا الحلوة والجزر","sweet carrot")};
 public static Food food(String id){for(Food f:FOODS)if(f.id.equals(id))return f;return null;}
 public static Recipe recipe(int id){return id>=0&&id<RECIPES.length?RECIPES[id]:null;}
 public static boolean eligible(Recipe r,Set<String> avoided,Set<String> introduced){
 for(String id:r.foods){Food f=food(id);if(avoided.contains(id)||(!f.allergen.isEmpty()&&!introduced.contains(id)))return false;}return true;
 }
 public static Recipe choose(LocalDate birth,LocalDate day,int slot,int override,Set<String> avoided,Set<String> introduced){
 if(!Plan.supported(birth,day))return null;
 Recipe picked=recipe(override);if(picked!=null&&picked.slot==slot&&eligible(picked,avoided,introduced))return picked;
 List<Recipe> pool=new ArrayList<>();for(Recipe r:RECIPES)if(r.slot==slot&&eligible(r,avoided,introduced))pool.add(r);
 return pool.isEmpty()?null:pool.get(Math.floorMod(ChronoUnit.DAYS.between(birth.plusMonths(6),day),pool.size()));
 }
 public static String ingredients(Recipe r){List<String> names=new ArrayList<>();for(String id:r.foods)names.add(food(id).name);return String.join("، ",names);}
 public static String instructions(Recipe r,int age){StringBuilder b=new StringBuilder();int n=1;for(String id:r.foods)b.append(n++).append(". ").append(food(id).prep).append("\n\n");
 b.append(n).append(". امزجي المكونات المحضرة، ثم عدلي القوام بقليل من ماء الطبخ واتركي الطعام يبرد قبل تقديمه.\n\n");
 b.append(age<9?"القوام: اهرسي الطعام حسب قدرة طفلتك، وانتقلي تدريجيا إلى قوام أخشن عندما تصبح مستعدة؛ لا تبقيه ناعما لمجرد عمرها.":"القوام: قدمي مهروسا أكثر خشونة حسب قدرتها. يجب أن يكون الطعام طريا جدا وخاليا من القطع القاسية والعظام والشوك.");
 b.append("\n\nابدئي بكمية صغيرة وزيدي حسب شهيتها. لا تضيفي الملح أو السكر أو العسل أو مكعبات المرق. اجعليها جالسة وراقبيها طوال الوجبة. استعملي في الخلطات أطعمة سبق أن تحملتها. الصور للتعرف على المكونات وليست شكلا مقترحا لتقديمها.");return b.toString();}
}
