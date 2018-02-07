package com.example.atoi.mvptrial.di.scopes;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by atoi on 6.02.2018.
 */

@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {
}
