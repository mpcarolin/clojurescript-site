(ns my-site.components.blog
  (:require [reagent.core :as r]
            [clojure.string :as s]
            [cljs.spec.alpha :as spec]))


(declare get-date)
;;
;; Data / specs
;;

(def date (get-date))

(spec/def ::post (spec/keys :req-un [::title ::body ::date]))
(defonce posts [{:title "Functional Programming is Practical"
                 :body [:p "In my opinion..."]
                 :date date}])

;;
;; Input
;;

(spec/fdef load-blog
           :args (spec/cat :title (spec/and string?
                                            #(-> % count pos?)))
           :ret ::post)
(defn load-blog!
  [title]
  (first posts))

;;
;; Interop
;;

(defn get-date [] (->> (js/Date.)
                       (.toDateString)))

(defn get-url [] (aget js/window "location" "href"))

(defn last-param
  [url]
  (let [params (s/split url "/")]
    (last params)))


;;
;; Component
;;
(defn content
  [{:keys [title date body]}]
  [:div
    [:h2 title]
    [:hr]
    [:p.date date]
    body])

(defn blog-page
  []
  (let [blog-title (last-param (get-url))
        blog (load-blog! blog-title)]
    [:div.center-column
    [:a {:href "/archive"
         :style {:font-size "0.75em"}}
        "archive"]
    (content blog)]))

