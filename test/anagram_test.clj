(ns anagram-test
  (:require [clojure.test :refer :all])
  (:require [anagram :refer [are-anagrams]]))

(deftest basic-cases
  (is (= false (are-anagrams "" "")))
  (is (= false (are-anagrams nil nil)))
  (is (= false (are-anagrams nil " ")))
  (is (= false (are-anagrams "a" "b")))
  (is (= true (are-anagrams "a" "a")))
  (is (= true (are-anagrams "paris" "sirap"))))

(deftest whitespace-ignored
  (is (= true (are-anagrams "w " "w")))
  (is (= true (are-anagrams "w" "w " " w " " w"))))

(deftest order-not-important
  (is (= true (are-anagrams "ab" "ba")))
  (is (= true (are-anagrams "paa" "apa" "aap"))))

(deftest not-character-sensitive
  (is (= true (are-anagrams "A" "a")))
  (is (= true (are-anagrams "A" "A" "a"))))

(deftest apostrophes-stripped
  (is (= true (are-anagrams "a's" "as"))))

(deftest special-character-words-ignored
  (is (= false (are-anagrams "∞a" "a")))
  (is (= false (are-anagrams "正a" "a")))
  (is (= false (are-anagrams "å" "")))
  (is (= false (are-anagrams "å" nil)))
  (is (= false (are-anagrams "å" " "))))

(run-tests)