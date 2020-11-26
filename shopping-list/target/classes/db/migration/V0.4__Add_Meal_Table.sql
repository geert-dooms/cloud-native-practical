create table MEAL (
                          ID UUID PRIMARY KEY,
                          ID_MEALDB TEXT,
                          NAME TEXT,
                          INGREDIENTS TEXT,
                          INSTRUCTIONS TEXT,
                          IMAGE TEXT
);

create table MEAL_SHOPPING_LIST (
                                        MEAL_ID UUID REFERENCES meal (ID),
                                        SHOPPING_LIST_ID UUID REFERENCES shopping_list (id),
                                        CONSTRAINT MEAL_SHOPPING_LIST_PKEY PRIMARY KEY (MEAL_ID, SHOPPING_LIST_ID)
);