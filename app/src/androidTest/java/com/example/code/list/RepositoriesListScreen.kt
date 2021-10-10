package com.example.code.list

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.example.code.R
import org.hamcrest.Matcher

internal class RepositoriesListScreen : Screen<RepositoriesListScreen>() {

  val list: KRecyclerView = KRecyclerView(builder = { withId(R.id.list) }, itemTypeBuilder = { itemType(::Item) })
}

class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {

  val title = KTextView(parent) { withId(R.id.repositoryTitle) }
  val description = KTextView(parent) { withId(R.id.repositoryDescription) }
}