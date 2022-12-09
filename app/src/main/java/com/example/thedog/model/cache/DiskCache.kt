package com.example.thedog.model.cache

import android.util.Log
import com.example.thedog.TheDogApplication
import java.io.*

/**
 * Created by pedrooliveira on 08/12/2022
 * All rights reserved GoodBarber
 */
object DiskCache {
    private val TAG = DiskCache::class.simpleName
    val rootDir: File = TheDogApplication.initialize.cacheDir

    /**
     * Saves an object into disk cache
     * */
    @Synchronized
    fun saveObject(obj: Any, filename: String): Boolean {
        return try {
            val itemFile: File = getFile(filename)
            val fos = FileOutputStream(itemFile)
            val oos = ObjectOutputStream(fos)
            oos.apply {
                writeObject(obj)
                close()
            }
            fos.close()
            Log.d(TAG, "Object written to disk (" + itemFile.absolutePath.toString() + ")")
            true
        } catch (e: Exception) {
            Log.d(TAG, "Impossible to save  $filename", e)
            false
        }
    }

    /**
     * Gets an object from diskcache corresponding to a filename
     * */
    @Synchronized
    fun getObject(filename: String): Any? {
        val f = getFile(filename)
        var content: Any? = null
        if (f.exists()) {
            try {
                var ois: ObjectInputStream? = null
                try {
                    ois = ObjectInputStream(BufferedInputStream(FileInputStream(f)))
                    content = ois.readObject()
                } finally {
                    ois?.close()
                }
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "Error while opening to open object in cache :$filename", e)
            }
        } else {
            Log.d(TAG, "Object does not exist in cache :$filename")
        }
        return content
    }

    @Synchronized
    fun getFile(filename: String): File {
        val file = File(rootDir, filename)
        if (!file.parentFile?.exists()!!) {
            file.parentFile?.mkdirs()
        }
        return file
    }
}