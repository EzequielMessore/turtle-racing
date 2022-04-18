package br.com.messore.tech.turtleracing.data.local.extensions

import androidx.room.migration.Migration

operator fun Migration.plus(migration: Migration): List<Migration> = listOf(this, migration)
