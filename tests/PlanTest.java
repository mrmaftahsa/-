import ma.baby.meals.Plan;
import java.time.LocalDate;
public class PlanTest {
 static void check(boolean v){if(!v)throw new AssertionError();}
 public static void main(String[] a){
 LocalDate b=LocalDate.of(2026,5,3);
 check(!Plan.supported(b,b.plusMonths(6).minusDays(1)));
 check(Plan.supported(b,b.plusMonths(6)));
 check(Plan.supported(b,b.plusYears(1).minusDays(1)));
 check(!Plan.supported(b,b.plusYears(1)));
 check(Plan.months(b,LocalDate.of(2026,11,2))==5);
 check(Plan.months(b,LocalDate.of(2026,11,3))==6);
 for(int n=1;n<=3;n++){int count=0;for(int s=0;s<3;s++)if(Plan.active(n,s))count++;check(count==n);}
 for(LocalDate d=b.plusMonths(6);d.isBefore(b.plusYears(1));d=d.plusDays(1))for(int s=0;s<3;s++)check(!Plan.meal(b,d,s).isEmpty());
 check(Plan.meal(b,b.plusMonths(6),0).equals(Plan.meal(b,b.plusMonths(6).plusDays(7),0)));
 check(Plan.supported(LocalDate.of(2024,8,31),LocalDate.of(2025,2,28)));
 System.out.println("Planner checks passed: age boundaries, month-end birth, slots, daily coverage, weekly rotation.");
 }
}