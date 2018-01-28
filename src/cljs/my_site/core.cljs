(ns my-site.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [my-site.components.home :refer [home-page]]
              [my-site.components.about :refer [about-page]]
              [my-site.components.navigation :refer [nav-bar]] 
              [accountant.core :as accountant]))

;; -------------------------
;; Routes

(defonce page (atom #'home-page))
(defonce route (atom "/"))
(defn update-page!
  [page route]
  (reset! page page)
  (reset! route route))

(defn current-page []
  [:div.root-div
   [nav-bar]
   [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))

(secretary/defroute "/about" []
  (reset! page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
