package com.edavtyan.materialplayer2.lib.file_storage;

import android.content.Context;

import com.ed.libsutils.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class BaseFileStorage {
	private final File dir;

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public BaseFileStorage(Context context, String dirName) {
		dir = new File(context.getCacheDir(), dirName);
		dir.mkdirs();
	}

	public void saveBytes(String filename, byte[] data) {
		filename = encode(filename);
		File file = new File(dir, filename);
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(data, 0, data.length);
			fileOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			IOUtils.closeStream(fileOutputStream);
		}
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public byte[] loadBytes(String filename) {
		filename = encode(filename);
		File file = new File(dir, filename);
		byte[] data = new byte[(int) file.length()];
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(data, 0, data.length);
			return data;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			IOUtils.closeStream(fileInputStream);
		}
	}

	public boolean exists(String filename) {
		return new File(dir, encode(filename)).exists();
	}

	public String[] list() {
		return dir.list();
	}

	public void delete(int position) {
		dir.listFiles()[position].delete();
	}

	public String getFullPath(String filename) {
		return new File(dir, encode(filename)).getAbsolutePath();
	}

	private String encode(String filename) {
		return filename.replace("/", "-");
	}
}
