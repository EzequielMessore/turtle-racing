package br.com.messore.tech.turtleracing.data.local.extensions

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

internal fun SupportSQLiteDatabase.insert(table: String, values: ContentValues.() -> Unit) {
    insert(table, SQLiteDatabase.CONFLICT_FAIL, ContentValues().apply(values))
}
