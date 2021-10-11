package coffeecord;

import config.Config;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util {
	public static boolean isSuperuser(User user) {
		return user.getId().equals(Config.get("OWNER_ID"));
	}

	public static String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String getCurrentPackageName(Object object) {
		var fullPackageName = object.getClass().getPackageName();
		var packageName = fullPackageName.substring(fullPackageName.lastIndexOf(".") + 1);
		return packageName;
	}

	public static List<String> getCurrentPackageName(List<Object> objects) {
		List<String> packageNames = new ArrayList<>();
		for(var obj: objects) {
			packageNames.add(getCurrentPackageName(obj));
		}
		return packageNames;
	}

	public static <V> HashMap<String, List<V>> getCurrentPackageHashmap(List<V> objects) {
		HashMap<String, List<V>> map = new HashMap<>();

		for(var obj: objects) {
			var packageName = getCurrentPackageName(obj);

			if(map.containsKey(packageName))
				map.get(packageName).add(obj);
			else
				map.put(packageName, new ArrayList<V>(){{ add(obj); }});
		}

		return map;
	}
}
