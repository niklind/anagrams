(ns file-reader
  (:require [clojure.java.io :as io]))

(defn read-lines [file]
  (with-open [reader (io/reader file)]
    (into [] (line-seq reader))))

(defn -main []
  (println (count (read-lines "res/wordlist.txt"))))