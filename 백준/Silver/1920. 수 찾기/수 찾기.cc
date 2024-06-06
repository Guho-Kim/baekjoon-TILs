#include <iostream>
#include<algorithm>
using namespace std;
int n, m;
int arr[100001];
void binary_search(int key) {
	int start = 0;
	int end = n - 1;
	int mid;

	while (end - start >= 0) {
		mid = (start + end) / 2;
		if (arr[mid] == key) {
			cout << 1 <<'\n';
			return;
			
		}
		else if (arr[mid] > key) {
			end = mid - 1;
		}
		else if (arr[mid] < key) {
			start = mid + 1;
		}
	}

	cout << 0 << '\n';
	return;
}
int main() {
	cin.tie(0); ios::sync_with_stdio(0);

	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		
	}
	sort(arr, arr + n);
	cin >> m;
	for (int i = 0; i < m; i++) {
		int k; cin >> k;
		binary_search(k);

	}



	return 0;
}