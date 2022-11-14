# store-management-service

Decription: Store management tool

Operations: 
1. Add a new product to the store
    Method: POST
    Request:
      - url: /api/v1/product
      - content-type: application/json
      - body: Product.class
2. Update an existing product
    Method: PUT
    Request:
      - url: /api/v1/product
      - content-type: application/json
      - body: Product.class
3. Finds products by status
    Method: GET
    Request:
      - url: /api/v1/product?status={status}
4. Find product by ID
    Method: GET
    Request:
      - url: /api/v1/product/{id}
5. Delete product
    Method: DELETE
    Request:
      - url: /api/v1/product/{id}
      
      
