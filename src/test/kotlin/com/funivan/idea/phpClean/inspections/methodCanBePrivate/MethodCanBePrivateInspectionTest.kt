package com.funivan.idea.phpClean.inspections.methodCanBePrivate

import com.funivan.idea.phpClean.BaseInspectionTest

class MethodCanBePrivateInspectionTest : BaseInspectionTest() {

    fun testFindMethodsThatCanBePrivate() {
        assert(
                MethodCanBePrivateInspection(),
                """<?php
                final class User {
                  protected function <warning descr="Method can be private">name</warning>() {}
                }
                """,
                """<?php
                final class User {
                  private function name() {}
                }
                """
        )
    }

    fun testFindMethodsInNonFinalClass() {
        assert(
                MethodCanBePrivateInspection(),
                """<?php
                class A {
                  protected function name() {}
                }
                """
        )
    }

    fun testFindMethodsInClassWithExtends() {
        assert(
                MethodCanBePrivateInspection(),
                """<?php
                class Test{}
                final class Uid extends Test {
                  protected function id() {}
                }
                """
        )
    }

    fun testCheckPublicMethod() {
        assert(
                MethodCanBePrivateInspection(),
                """<?php
                final class Id {
                  public function __construct() {}
                  public function size() {}
                }
                """
        )
    }

}