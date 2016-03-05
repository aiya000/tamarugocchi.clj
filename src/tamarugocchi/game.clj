(ns tamarugocchi.game
  (:require [tamarugocchi.common.io :as io]))

(defn printUsage []
  (println "usage:")
  (println "た❍ごっちはなんかします。")
  (println "貴方はた❍ごっちの行動を選んでなんかしてください。"))

(defn doSleep [tama]
  (println "た❍ごっちは寝た。"))
(defn doEat [tama]
  (println "た❍ごっちは何かを食べた。"))
(defn doTraining [tama]
  (println "た❍ごっちは筋トレをした。 筋肉筋肉〜。"))
(defn doMakeToilet [tama]
  (println "た❍ごっちはトイレを致した。"))

(defn makeActionText [tama]
  (str (tama :name)
       "の行動を選んでください\n"
       "1: 寝る\n"
       "2: 食べる\n"
       "3: 筋トレする\n"
       "4: トイレする"))

(defn selectTamaAction [tama]
  (case (io/promptLn (makeActionText tama))
    "1" (doSleep tama)
    "2" (doEat tama)
    "3" (doTraining tama)
    "4" (doMakeToilet tama)
        ((println "!! 1,2,3,4のいずれかを選んでください。")
         (selectTamaAction tama))))

(defn startGame [tama]
  (printUsage)
  (println "- - - - -")
  (selectTamaAction tama))
