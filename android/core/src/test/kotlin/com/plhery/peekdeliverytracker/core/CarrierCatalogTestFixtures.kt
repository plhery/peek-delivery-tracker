package com.plhery.peekdeliverytracker.core


/** The catalogs CarrierCatalogTests.swift builds its dynamic catalogs from, as the same JSON text. */
internal object CarrierCatalogTestFixtures {
    val bundledCatalogData: ByteArray get() = SharedResources.open("CarrierCatalog.json").use { it.readBytes() }

    val futureCatalogData: ByteArray = """
    {
      "version": "test-v1",
      "x-carriers": {
        "future-express": {
          "displayName": "Future Express",
          "color": "#123456",
          "selectable": true,
          "timezone": "Europe/Paris",
          "tracking": {
            "mode": "automatic",
            "adapter": "future-express",
            "requirements": [{
              "field": "dpdPostcode",
              "validator": "swissPostcode",
              "optional": true,
              "label": "Delivery postcode",
              "type": "text",
              "pattern": "^[0-9]{4}$",
              "maxLength": 4,
              "inputMode": "numeric"
            }]
          },
          "trackingUrlTemplate": "https://tracking.future.example/{trackingNumber}",
          "trackingSearchUrl": "https://tracking.future.example/",
          "linkRules": [{
            "domains": ["tracking.future.example"],
            "path": "^/([^/?#]+)$"
          }, {
            "domains": ["tracking.future.example"],
            "query": "^(FX\\d{8})$"
          }],
          "detectionRules": [{
            "pattern": "^FX\\d{8}$",
            "confidence": "high"
          }]
        },
        "unknown": {
          "displayName": "Carrier",
          "color": "#8e8e93",
          "selectable": false,
          "timezone": "UTC",
          "tracking": { "mode": "link-only", "adapter": null },
          "linkRules": [],
          "detectionRules": []
        }
      }
    }
    """.trimIndent().toByteArray()

    /** A catalog from a newer release whose rules name a check this build does not have. */
    val unknownChecksumCatalogData: ByteArray = """
    {
      "x-carriers": {
        "future-express": {
          "displayName": "Future Express", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "future-express", "recognitionRank": 4 },
          "linkRules": [],
          "detectionRules": [{ "pattern": "^FX\\d{8}$", "confidence": "high", "checksum": "future-check" }]
        },
        "check-post": {
          "displayName": "Check Post", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "check-post", "recognitionRank": 3 },
          "linkRules": [],
          "detectionRules": [{ "pattern": "^\\d{10}$", "confidence": "low", "checksum": "mod7", "preferred": true }]
        },
        "plain-post": {
          "displayName": "Plain Post", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "plain-post", "recognitionRank": 2 },
          "linkRules": [],
          "detectionRules": [{ "pattern": "^(?:\\d{10}|[A-Z]{2}\\d{9}[A-Z]{2})$", "confidence": "low" }]
        },
        "future-post": {
          "displayName": "Future Post", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "future-post", "recognitionRank": 1 },
          "linkRules": [],
          "detectionRules": [{
            "pattern": "^(?:\\d{10}|[A-Z]{2}\\d{9}[A-Z]{2})$", "confidence": "low",
            "checksum": "future-check", "preferred": true
          }]
        },
        "intl-post": {
          "displayName": "Postal carrier", "color": "#123456", "selectable": false, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "universal" },
          "linkRules": [],
          "detectionRules": [{ "pattern": "^[A-Z]{2}\\d{9}[A-Z]{2}$", "confidence": "high", "checksum": "s10" }]
        },
        "unknown": {
          "displayName": "Carrier", "color": "#8e8e93", "selectable": false, "timezone": "UTC",
          "tracking": { "mode": "link-only", "adapter": null },
          "linkRules": [],
          "detectionRules": []
        }
      }
    }
    """.trimIndent().toByteArray()

    val recognitionPatternCatalogData: ByteArray = """
    {
      "x-carriers": {
        "pro-freight": {
          "displayName": "Pro Freight", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "pro-freight", "recognitionRank": 2, "recognitionPattern": "^\\d{11}$" },
          "linkRules": [],
          "detectionRules": [
            { "pattern": "^\\d{10}0\\d$", "confidence": "low", "checksum": "saia" },
            { "pattern": "^\\d{11}$", "confidence": "low" }
          ]
        },
        "plain-post": {
          "displayName": "Plain Post", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "plain-post", "recognitionRank": 1 },
          "linkRules": [],
          "detectionRules": [{ "pattern": "^\\d{11,12}$", "confidence": "low" }]
        },
        "unknown": {
          "displayName": "Carrier", "color": "#8e8e93", "selectable": false, "timezone": "UTC",
          "tracking": { "mode": "link-only", "adapter": null },
          "linkRules": [],
          "detectionRules": []
        }
      }
    }
    """.trimIndent().toByteArray()

    /** A newer catalog in which one carrier asks for an input this build does not know. */
    val unreadableCarrierCatalogData: ByteArray = """
    {
      "x-carriers": {
        "future-express": {
          "displayName": "Future Express", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "future-express" },
          "linkRules": [],
          "detectionRules": [{ "pattern": "^FX\\d{8}$", "confidence": "high" }]
        },
        "dpd": {
          "displayName": "DPD Renamed", "color": "#dc0032", "selectable": true, "timezone": "Europe/Zurich",
          "tracking": { "mode": "automatic", "adapter": "dpd", "requirements": [{
            "field": "recipientPhone", "validator": "phone", "label": "Phone", "type": "text"
          }] },
          "linkRules": [],
          "detectionRules": []
        },
        "unknown": {
          "displayName": "Carrier", "color": "#8e8e93", "selectable": false, "timezone": "UTC",
          "tracking": { "mode": "link-only", "adapter": null },
          "linkRules": [],
          "detectionRules": []
        }
      }
    }
    """.trimIndent().toByteArray()

    val linkRulesCatalogData: ByteArray = """
    {
      "x-carriers": {
        "bare-post": {
          "displayName": "Bare Post", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "bare-post" },
          "linkRules": [{ "domains": ["bare.example"], "query": "^(BP\\d{8})$" }],
          "detectionRules": [{ "pattern": "^BP\\d{8}$", "confidence": "high" }]
        },
        "market-one": {
          "displayName": "Market One", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "market-one" },
          "linkRules": [{ "domains": ["market.example"], "params": ["numer"], "ownNumbersOnly": true }],
          "detectionRules": [{ "pattern": "^MO\\d{8}$", "confidence": "high" }]
        },
        "page-post": {
          "displayName": "Page Post", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "page-post" },
          "trackingUrlTemplate": "https://track.portal.example/{trackingNumber}",
          "linkRules": [],
          "detectionRules": [{ "pattern": "^PP\\d{9}$", "confidence": "high" }]
        },
        "portal-post": {
          "displayName": "Portal Post", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "portal-post" },
          "trackingUrlTemplate": "https://portal.example/t/{trackingNumber}",
          "linkRules": [{ "domains": ["portal.example"], "path": "^/t/([^/]+)$" }],
          "detectionRules": [{ "pattern": "^QQ\\d{9}$", "confidence": "high" }]
        },
        "net-a": {
          "displayName": "Net A", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "net-a" },
          "linkRules": [{ "domains": ["net.example"], "params": ["id"], "detectFromNumber": true }],
          "detectionRules": [{ "pattern": "^N\\d{10}$", "confidence": "low" }]
        },
        "net-b": {
          "displayName": "Net B", "color": "#123456", "selectable": true, "timezone": "UTC",
          "tracking": { "mode": "automatic", "adapter": "net-b" },
          "linkRules": [{ "domains": ["net.example"], "params": ["id"], "detectFromNumber": true }],
          "detectionRules": [{ "pattern": "^N\\d{10}$", "confidence": "low" }]
        },
        "unknown": {
          "displayName": "Carrier", "color": "#8e8e93", "selectable": false, "timezone": "UTC",
          "tracking": { "mode": "link-only", "adapter": null },
          "linkRules": [],
          "detectionRules": []
        }
      }
    }
    """.trimIndent().toByteArray()
}
