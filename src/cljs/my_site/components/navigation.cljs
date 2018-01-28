(ns my-site.components.navigation)

;;
;; UI
;;

(defn link [title link] 
    [:a.nav-link {:href link} title])

(defn nav-bar []
  [:div.nav-container
    [:ul.nav-list
      [:li.nav-item.one (link "Home" "/")]
      [:li.nav-item.two (link "About" "/about")]
      [:hr.nav-underline]]])