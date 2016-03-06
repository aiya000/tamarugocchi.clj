(ns tamarugocchi.game
  (:require [tamarugocchi.common.io :as io]))

(defn printUsage []
  (println "usage:")
  (println "た❍ごっちはなんかします。")
  (println "貴方はた❍ごっちの行動を選んでなんかしてください。"))

(defn doSleep [tama]
  (println "た❍ごっちは寝た。")
  {:name     (tama :name)
   :hp       (+ (tama :hp) 20)
   :power    (- (tama :power) 10)
   :fatness  (tama :fatness)
   :ageAsDay (+ (tama :ageAsDay) 1)})
(defn doEat [tama]
  (println "た❍ごっちは何かを食べた。")
  {:name     (tama :name)
   :hp       (+ (tama :hp) 30)
   :power    (tama :power)
   :fatness  (+ (tama :fatness) 20)
   :ageAsDay (+ (tama :ageAsDay) 1)})
(defn doTraining [tama]
  (println "た❍ごっちは筋トレをした。 筋肉筋肉〜。")
  {:name     (tama :name)
   :hp       (- (tama :hp) 30)
   :power    (+ (tama :power) 40)
   :fatness  (- (tama :fatness) 10)
   :ageAsDay (+ (tama :ageAsDay) 1)})
(defn doMakeToilet [tama]
  (println "た❍ごっちはトイレを致した。")
  {:name     (tama :name)
   :hp       (tama :hp)
   :power    (- (tama :power) 20)
   :fatness  (- (tama :fatness) 20)
   :ageAsDay (+ (tama :ageAsDay) 1)})

(defn makeActionText [tama]
  (str (tama :name)
       "の行動を選んでください\n"
       "1: 寝る\n"
       "2: 食べる\n"
       "3: 筋トレする\n"
       "4: トイレする"))

(defn tamaStatus [tama]
  (str (tama :ageAsDay) "日目\n"
       "HP:     " (tama :hp) "\n"
       "力:     " (tama :power) "\n"
       "肥満度: " (tama :fatness)))

(defn selectTamaAction [tama]
  (println "- - - - -")
  (println (tamaStatus tama))
  (println "")
  (if (or (<= (tama :hp) 0) (>= (tama :fatness) 200))
    tama
    (case (io/promptLn (makeActionText tama))
      "1" (selectTamaAction (doSleep tama))
      "2" (selectTamaAction (doEat tama))
      "3" (selectTamaAction (doTraining tama))
      "4" (selectTamaAction (doMakeToilet tama))
      ; DEBUG
      "184" {:name (tama :name) :hp 0 :power (tama :power) :fatness (tama :fatness) :ageAsDay (tama :ageAsDay)}
      ((println "!! 1,2,3,4のいずれかを選んでください。")
       (selectTamaAction tama)))))

; TODO: tell 死因
(defn printGameResult [deadTama]
  (println
    (str (deadTama :name) "は"
         (deadTama :ageAsDay) "日間生活を営みました。\n"
         (deadTama :name) "の最終ステータスは\n  "
         "HP: " (deadTama :hp) "\n  "
         "力: " (deadTama :power) "\n  "
         "肥満度: " (deadTama :fatness) "\n"
         "でした。")))

(defn startGame [tama]
  (printUsage)
  (println "- - - - -")
  (let [deadTama (selectTamaAction tama)]
    (println (deadTama :name) "は安らかに眠った。")
    (println "")
    (printGameResult deadTama)))
