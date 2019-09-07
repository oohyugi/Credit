package com.yogi.credit.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction


/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */


fun Context.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}

fun FragmentActivity.replaceFragment(fragment: Fragment, idContainer: Int, tag: String?) {
    supportFragmentManager.beginTransaction()
        .replace(idContainer, fragment, tag)
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        .commit()

}

