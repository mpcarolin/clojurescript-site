(ns my-site.components.navigation)
;;
;; Data
;;

(def routes [{:path "/"      :title "home"  :id "one"}
             {:path "/blog"  :title "blog"  :id "two"}
             {:path "/about" :title "about" :id "three"}])
;;
;; UI
;;

(defn link [title link]
    [:a.nav-link {:href link} title])

(defn nav-li
  "Generates the li element of the given index for the navigation bar"
  [{:keys [title path id]} current-path]
  (let [current? (= path current-path)
        cls      (str "nav-item " (when current? "current ") id)]
    ^{:key id}
    [:li {:class cls} (link title path)]))

(defn nav-bar 
  "Generates the nav-bar element. Use the path argument to specify
   which nav bar element is the active page, to be highlighted"
  [path]
  [:div.nav-container
    [:ul.nav-list
      (for [route routes] (nav-li route path))]])
