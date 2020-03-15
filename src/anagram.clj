(ns anagram
  (:require [clojure.core :as core]
            [clojure.string :as string]
            [file-reader :as reader]
            [clojure.set :as set]))

(defn normalize [string]
  (-> string (string/replace #"[^a-zA-Z]" "")
             (string/lower-case)))

(defn is-valid [word]
  (if (and
        (not (string/blank? word))
        (core/re-matches #"^[a-zA-Z' ]+$" word))
    true
    false))

(defn hashcode [string]
  (if (is-valid string)
    (-> string (normalize)
               (core/sort)
               (string/join))
    nil))

(defn are-anagrams [a b & more]
  (let [words (map hashcode (into [a b] more))]
    (and (not-any? nil? words)
         (apply = words))))

(defn entry [word]
  (if (is-valid word)
    {(hashcode word) #{(normalize word)}}
    {}))

(defn anagram-sets [words]
  (map val
    (filter #(>= (count (val %)) 2)
      (apply merge-with set/union {}
        (map entry words)))))

(defn -main []
  (println
    (count
      (anagram-sets
        (reader/read-lines "res/wordlist.txt")))))