create table COCKTAIL (
                          ID UUID PRIMARY KEY,
                          ID_DRINK TEXT,
                          NAME TEXT,
                          INGREDIENTS TEXT
);

create table COCKTAIL_SHOPPING_LIST (
  COCKTAIL_ID UUID REFERENCES cocktail (ID),
  SHOPPING_LIST_ID UUID REFERENCES shopping_list (id),
  CONSTRAINT COCKTAIL_SHOPPING_LIST_PKEY PRIMARY KEY (COCKTAIL_ID, SHOPPING_LIST_ID)
);