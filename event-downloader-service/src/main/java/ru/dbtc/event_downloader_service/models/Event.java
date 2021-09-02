package ru.dbtc.event_downloader_service.models;

import java.time.LocalDateTime;

/*"id": 1752676,
        "starts_at": "2021-09-01T10:15:00+0300",
        "name": "Лекция В.О. Никишиной (офлайн)",
        "url": "https://fakultet-mirovoy-ek-event.timepad.ru/event/1752676/",
        "poster_image": {
        "default_url": "https://ucare.timepad.ru/e8e95ba1-3213-4ba4-8c21-d85ea2da3c22/-/preview/308x600/-/format/jpeg/poster_event_1752676.jpg",
        "uploadcare_url": "//ucare.timepad.ru/e8e95ba1-3213-4ba4-8c21-d85ea2da3c22/"
        },
        "location": {
        "country": "Россия",
        "city": "Москва",
        "address": "Малая Ордынка 29, актовый зал (ауд. 306)"
        },
        "categories": [
        {
        "id": 2465,
        "name": "Наука"
        }
        ],
        "tickets_limit": 55,
        "ticket_types": [
        {
        "id": 4209171,
        "name": "Входной билет",
        "description": "",
        "buy_amount_min": 1,
        "buy_amount_max": 30,
        "price": 0,
        "is_promocode_locked": false,
        "sale_ends_at": "2021-09-01T12:00:00+0300",
        "is_active": true,
        "ad_partner_profit": 0,
        "send_personal_links": false,
        "status": "ok"
        }
        ],
        "age_limit": 12,
        "moderation_status": "featured"
        }*/

public class Event {
    private String title;
    private String category;
    private int ageLimit;
    private boolean isFree;
    private String description;
    private LocalDateTime startEvent;
    private String country; //todo Check if there are events in other countries
    private String city;
    private String address;

}
