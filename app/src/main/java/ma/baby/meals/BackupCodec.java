package ma.baby.meals;
import java.util.*;import java.io.*;import java.time.*;
/** Versioned typed preference snapshot. Decode validates before any storage mutation. */
public final class BackupCodec {
 public static String encode(Map<String,?> map)throws IOException{Properties p=new Properties();p.setProperty("format","luqmati-1");for(Map.Entry<String,?> e:map.entrySet()){Object v=e.getValue();if(!(v instanceof Boolean||v instanceof Integer||v instanceof String))throw new IOException("Unsupported value");String tag=v instanceof Boolean?"b:":v instanceof Integer?"i:":"s:";p.setProperty(e.getKey(),tag+v);}StringWriter out=new StringWriter();p.store(out,"Luqmati private backup");return out.toString();}
 public static Map<String,Object> decode(String data)throws IOException{try{if(data.length()>2000000)throw new IOException();Properties p=new Properties();p.load(new StringReader(data));if(!"luqmati-1".equals(p.remove("format"))||p.size()>20000)throw new IOException();Map<String,Object> result=new HashMap<>();for(String key:p.stringPropertyNames()){String value=p.getProperty(key);if(value.length()<2||value.charAt(1)!=':')throw new IOException();Object decoded;String raw=value.substring(2);switch(value.charAt(0)){case 'b':if(!raw.equals("true")&&!raw.equals("false"))throw new IOException();decoded=Boolean.parseBoolean(raw);break;case 'i':decoded=Integer.parseInt(raw);break;case 's':decoded=raw;break;default:throw new IOException();}validate(key,decoded);result.put(key,decoded);}return result;}catch(RuntimeException e){throw new IOException("Invalid backup",e);}}
 private static void validate(String key,Object value)throws IOException{
 if(key.equals("birth")){if(!(value instanceof String))throw new IOException();LocalDate birth=LocalDate.parse((String)value);if(birth.isAfter(LocalDate.now())||birth.isBefore(LocalDate.of(1900,1,1)))throw new IOException();return;}
 if(key.equals("diary")){if(!(value instanceof String)||((String)value).length()>1800000)throw new IOException();return;}
 if(key.equals("enabled")){bool(value);return;}
 if(key.equals("count")){integer(value,1,3);return;}
 if(key.matches("time[012]")){integer(value,0,1439);return;}
 String[] bits=key.split(":");if(bits.length==2){switch(bits[0]){case "favorite":if(Catalog.recipe(Integer.parseInt(bits[1]))==null)throw new IOException();bool(value);return;case "avoid":case "introduced":if(Catalog.food(bits[1])==null)throw new IOException();bool(value);return;default:throw new IOException();}}
 if(bits.length==3){LocalDate.parse(bits[1]);switch(bits[0]){case "done":integer(Integer.parseInt(bits[2]),0,2);bool(value);return;case "swap":int slot=Integer.parseInt(bits[2]);integer(slot,0,2);if(!(value instanceof Integer))throw new IOException();Catalog.Recipe r=Catalog.recipe((Integer)value);if(r==null||r.slot!=slot)throw new IOException();return;case "bought":if(Catalog.food(bits[2])==null)throw new IOException();bool(value);return;default:throw new IOException();}}
 throw new IOException("Unknown setting");
 }
 private static void bool(Object v)throws IOException{if(!(v instanceof Boolean))throw new IOException();}
 private static void integer(Object v,int min,int max)throws IOException{if(!(v instanceof Integer)||(Integer)v<min||(Integer)v>max)throw new IOException();}
}
