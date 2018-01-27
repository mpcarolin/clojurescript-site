(ns ^:figwheel-no-load my-site.dev
  (:require
    [my-site.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
