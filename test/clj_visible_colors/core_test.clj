(ns clj-visible-colors.core-test
  (:require [clojure.test :refer :all]
            [clj-visible-colors.core :refer :all]))

(let [black (->Color 0.0 0.0 0.0) white (->Color 1.0 1.0 1.0)]
  (deftest has-visible-brightness-contrast-test
    (testing "pure black and pure white have visible brightness"
      (is (has-visible-brightness-contrast black white))))

  (deftest has-visible-hue-contrast-test
    (testing "pure black and pure white have visible hue contrast"
      (is (has-visible-hue-contrast black white))))
  )
