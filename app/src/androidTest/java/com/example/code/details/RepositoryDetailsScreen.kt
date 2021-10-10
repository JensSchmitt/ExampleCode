package com.example.code.details

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.example.code.R

internal class RepositoryDetailsScreen : Screen<RepositoryDetailsScreen>() {

  val titleLabel: KTextView = KTextView { withId(R.id.titleLabel) }
  val titleText: KTextView = KTextView { withId(R.id.titleText) }
  val starsLabel: KTextView = KTextView { withId(R.id.starsLabel) }
  val starsText: KTextView = KTextView { withId(R.id.starsText) }
  val descriptionLabel: KTextView = KTextView { withId(R.id.descriptionLabel) }
  val descriptionText: KTextView = KTextView { withId(R.id.descriptionText) }
}