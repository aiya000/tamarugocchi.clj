(ns tamarugocchi.common.io)

(defn prompt [msg]
  (print msg ": ")
  (flush)
  (read-line))

(defn promptLn [msg]
  (println msg)
  (print ": ")
  (flush)
  (read-line))
