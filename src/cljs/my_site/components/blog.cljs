(ns my-site.components.blog
  (:require [reagent.core :as r]
            [my-site.services.bootstrap :as b]))

;;
;; Data 
;;
(defn get-date [] (->> (js/Date.)
                       (.toDateString)))

(defonce posts [{:title "Functional Programming is Practical"
                 :date (get-date)
                 :body [:p "In my opinion..."]}])

;;
;; Bootstrap bindings
;;
(defonce Grid (b/get-bootstrap "Grid"))
(defonce Row (b/get-bootstrap "Row"))
(defonce Col (b/get-bootstrap "Col"))

(defn side-bar []
  [:div
   [:p "This is a sidebar"]])

(defn content 
  [{:keys [title date body]}]
  [:div
    [:h2 title]
    [:hr]
    [:p.date date]
    body])

(defn blog-page
  []
  [:div.blog-container
   [Grid
    [Row {:class-name "show-grid"}
     [Col {:md 9}
      (content (posts 0))]
     [Col {:md 3}
      (side-bar)]]]])
        
