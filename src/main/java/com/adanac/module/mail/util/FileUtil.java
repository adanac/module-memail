package com.adanac.module.mail.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {

	public static File fileExist(String mouseFile) throws IOException {
		File file = new File(mouseFile);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}
}
