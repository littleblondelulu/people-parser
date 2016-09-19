(ns people-parser.core
  (:require [clojure.string :as str] [org.clojure/data.json])
  (:gen-class))

(defn -main []
  ;let variable params will not be available outside the let
  (println "Type a country name:")
  (let [people (slurp "people.csv")
        people (str/split-lines people)
        people (map (fn [line]
                      (str/split line #","))
                    people)
        header (first people)
        people (rest people)
        people (map (fn [line]
                      (zipmap header line))
                    people)
        country-name (read-line)
        people (filter (fn [line]
                         (= (get line "country") country-name))
                       people)
        file-text (json/write-str people)]
    (spit "filtered_people.json" file-text)))

; (defn foo
  ;line below is what shows up whhen you type in doc foo
  ; "I don't do a whole lot."
;[x]
; (defn -main [] (println "Hello, World! My name is Lindsey"))
;arity - exception that means you called a function with the wrong # of params

;lein uber -jar
;java - jar target people....