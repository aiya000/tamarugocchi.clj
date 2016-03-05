(ns tamarugocchi.clj
  (:gen-class)
  (:require [tamarugocchi.common.io :as io]
            [tamarugocchi.game      :as game]))

(def defaultTamaHP 100)
(def defaultTamaPower 10)
(def defaultTamaFatness 10)

(defn createTama []
  (let [[tamaName]
        [(io/prompt "た❍ごっちの名前を入力してください")]]
    {:name tamaName
     :hp defaultTamaHP
     :power defaultTamaPower
     :fatness defaultTamaFatness}))

(defn -main [& args]
  (let [tama (createTama)]
    (println "名前: " (tama :name))
    (println "HP: " (tama :hp))
    (println "力 " (tama :power))
    (println "肥満度 " (tama :fatness))
    (println "た❍ごっちを始めます。")
    (println "- - - - -")
    (game/startGame tama)))
