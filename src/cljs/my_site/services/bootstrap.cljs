(ns my-site.services.bootstrap
  (:require [cljsjs.react-bootstrap]
            [reagent.core :as r]))

;;
;; Bootstrap bindings
;;
(defn get-bootstrap [el-name] (r/adapt-react-class (aget js/ReactBootstrap el-name)))

