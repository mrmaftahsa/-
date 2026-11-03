import ma.baby.meals.*;import java.time.*;import java.util.*;import java.io.*;
public class FeatureTest {
 static void check(boolean b){if(!b)throw new AssertionError();}
 static void rejects(String s)throws Exception{try{BackupCodec.decode(s);throw new AssertionError("Invalid backup accepted");}catch(IOException expected){}}
 public static void main(String[] args)throws Exception{
 LocalDate b=LocalDate.of(2026,5,3),d=b.plusMonths(6);Set<String> empty=new HashSet<>(),introduced=new HashSet<>(Arrays.asList("egg","yogurt","couscous","fish"));Set<Integer> unique=new HashSet<>();
 for(Catalog.Recipe r:Catalog.RECIPES){check(unique.add(r.id));for(String f:r.foods)check(Catalog.food(f)!=null);check(Catalog.instructions(r,6).contains("القوام"));}check(unique.size()==36);
 for(int s=0;s<3;s++)for(int n=0;n<180;n++){Catalog.Recipe r=Catalog.choose(b,d.plusDays(n),s,-1,empty,empty);check(r!=null&&r.slot==s);for(String f:r.foods)check(Catalog.food(f).allergen.isEmpty());}
 check(Catalog.choose(b,b.plusYears(1),0,-1,empty,empty)==null);check(Catalog.choose(b,d,0,4,empty,introduced).id==4);check(Catalog.choose(b,d,0,4,empty,empty).id!=4);check(Catalog.choose(b,d,0,4,new HashSet<>(Arrays.asList("yogurt")),introduced).id!=4);
 Set<String> all=new HashSet<>();for(Catalog.Food f:Catalog.FOODS)all.add(f.id);check(Catalog.choose(b,d,0,-1,all,introduced)==null);check(Catalog.choose(b,d,0,12,empty,introduced).slot==0);
 Map<String,Object> m=new HashMap<>();m.put("birth","2026-05-03");m.put("count",3);m.put("enabled",true);m.put("time0",540);m.put("diary","[{\"notes\":\"جزر وإجاص\\nملاحظة\"}]");m.put("swap:2026-11-03:0",4);m.put("done:2026-11-03:1",true);m.put("introduced:egg",true);m.put("bought:2026-11-03:pear",false);m.put("favorite:4",true);check(BackupCodec.decode(BackupCodec.encode(m)).equals(m));
 rejects("format=foreign\ncount=i:2");rejects("format=luqmati-1\ncount=i:9");rejects("format=luqmati-1\ntime0=i:1440");rejects("format=luqmati-1\nenabled=b:yes");rejects("format=luqmati-1\nbirth=s:2026-02-30");rejects("format=luqmati-1\nunknown=s:x");m.clear();m.put("swap:2026-11-03:0",12);rejects(BackupCodec.encode(m));
 System.out.println("PASS: catalog, daily coverage, allergen gates, exclusions, swaps, Arabic backup round-trip and invalid backups.");
 }
}
